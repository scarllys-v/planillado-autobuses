package com.planillado.servlets;

import com.planillado.dao.RutasDAO;
import com.planillado.model.rutas;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rutas/*")

public class RutasServlet extends HttpServlet{
    private RutasDAO rutasDAO;

    @Override
    public void init() {
        rutasDAO = new RutasDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {
            // Listar todas las rutas
            List<rutas> listaRutas = rutasDAO.getAllRutas();
            req.setAttribute("rutas", listaRutas);
            req.getRequestDispatcher("/views/admin/rutas/listar.jsp").forward(req, resp);

        } else if (action.equals("/nuevo")) {
            // Mostrar formulario nueva ruta
            req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);

        } else if (action.equals("/editar")) {
            // Editar ruta
            int id = Integer.parseInt(req.getParameter("id"));
            rutas ruta = rutasDAO.getRutaById(id);
            req.setAttribute("ruta", ruta);
            req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);

        } else if (action.equals("/eliminar")) {
            // Eliminar ruta
            int id = Integer.parseInt(req.getParameter("id"));
            rutasDAO.deleteRuta(id);
            resp.sendRedirect(req.getContextPath() + "/rutas");

        } else if (action.equals("/buscar")) {
            // Buscar rutas por origen o destino
            String origen = req.getParameter("origen");
            String destino = req.getParameter("destino");

            if (origen != null && !origen.isEmpty()) {
                List<rutas> rutasEncontradas = rutasDAO.getRutasByOrigen(origen);
                req.setAttribute("rutas", rutasEncontradas);
            } else if (destino != null && !destino.isEmpty()) {
                List<rutas> rutasEncontradas = rutasDAO.getRutasByDestino(destino);
                req.setAttribute("rutas", rutasEncontradas);
            }

            req.getRequestDispatcher("/views/admin/rutas/listar.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action.equals("/guardar")) {
            String idParam = req.getParameter("id");
            String nombreRuta = req.getParameter("nombre_ruta");
            String origen = req.getParameter("origen");
            String destino = req.getParameter("destino");
            int distanciaKm = Integer.parseInt(req.getParameter("distancia_km"));

            rutas ruta = new rutas();
            ruta.setNombreRuta(nombreRuta);
            ruta.setOrigen(origen);
            ruta.setDestino(destino);
            ruta.setDuracionEstimada(distanciaKm);

            boolean resultado;

            if (idParam != null && !idParam.isEmpty()) {
                // Actualizar
                ruta.setIdRuta(Integer.parseInt(idParam));
                resultado = rutasDAO.updateRuta(ruta);
            } else {
                // Crear nueva
                // Verificar si ya existe
                if (rutasDAO.existeRuta(nombreRuta)) {
                    req.setAttribute("error", "Ya existe una ruta con ese nombre");
                    req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);
                    return;
                }
                resultado = rutasDAO.insertRuta(ruta);
            }

            if (resultado) {
                resp.sendRedirect(req.getContextPath() + "/rutas");
            } else {
                req.setAttribute("error", "Error al guardar la ruta");
                req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);
            }
        }
    }
}
