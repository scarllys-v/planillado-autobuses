package com.planillado.servlets;

import com.planillado.dao.RutasDAO;
import com.planillado.model.Rutas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rutas/*")
public class RutasServlet extends HttpServlet {

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

            List<Rutas> listaRutas = rutasDAO.getAllRutas();
            req.setAttribute("rutas", listaRutas);
            req.getRequestDispatcher("/views/admin/rutas/listar.jsp").forward(req, resp);

        } else if (action.equals("/nuevo")) {

            req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);

        } else if (action.equals("/editar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Rutas ruta = rutasDAO.getRutaById(id);
            req.setAttribute("ruta", ruta);
            req.getRequestDispatcher("/views/admin/rutas/formulario.jsp").forward(req, resp);

        } else if (action.equals("/eliminar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            rutasDAO.deleteRuta(id);
            resp.sendRedirect(req.getContextPath() + "/rutas");

        } else if (action.equals("/buscar")) {

            String origen = req.getParameter("origen");
            String destino = req.getParameter("destino");

            if (origen != null && !origen.isEmpty()) {
                List<Rutas> rutasEncontradas = rutasDAO.getRutasByOrigen(origen);
                req.setAttribute("rutas", rutasEncontradas);
            } else if (destino != null && !destino.isEmpty()) {
                List<Rutas> rutasEncontradas = rutasDAO.getRutasByDestino(destino);
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

            Rutas ruta = new Rutas();
            ruta.setNombreRuta(nombreRuta);
            ruta.setOrigen(origen);
            ruta.setDestino(destino);
            ruta.setDuracionEstimada(distanciaKm);

            boolean resultado;

            if (idParam != null && !idParam.isEmpty()) {

                ruta.setIdRuta(Integer.parseInt(idParam));
                resultado = rutasDAO.updateRuta(ruta);

            } else {

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