package com.planillado.servlets;

import com.planillado.dao.RecorridoDAO;
import com.planillado.model.recorridos;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/recorridos/*")

public class RecorridosServlet extends HttpServlet {
    private RecorridoDAO recorridoDAO;

    @Override
    public void init() {
        recorridoDAO = new RecorridoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {
            // Listar todos los recorridos
            List<recorridos> recorridos = recorridoDAO.getAllRecorridos();
            req.setAttribute("recorridos", recorridos);
            req.getRequestDispatcher("/views/admin/recorridos/listar.jsp").forward(req, resp);

        } else if (action.equals("/nuevo")) {
            // Mostrar formulario
            req.getRequestDispatcher("/views/admin/recorridos/formulario.jsp").forward(req, resp);

        } else if (action.equals("/editar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            recorridos recorrido = recorridoDAO.getRecorridoById(id);
            req.setAttribute("recorrido", recorrido);
            req.getRequestDispatcher("/views/admin/recorridos/formulario.jsp").forward(req, resp);

        } else if (action.equals("/eliminar")) {
            int id = Integer.parseInt(req.getParameter("id"));
            recorridoDAO.deleteRecorrido(id);
            resp.sendRedirect(req.getContextPath() + "/recorridos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action.equals("/guardar")) {
            String idParam = req.getParameter("id");
            int idBus = Integer.parseInt(req.getParameter("id_bus"));
            int idRuta = Integer.parseInt(req.getParameter("id_ruta"));
            int idConductor = Integer.parseInt(req.getParameter("id_conductor"));
            Date fecha = Date.valueOf(req.getParameter("fecha"));
            Time horaInicio = Time.valueOf(req.getParameter("hora_inicio"));
            Time horaFin = Time.valueOf(req.getParameter("hora_fin"));
            String estado = req.getParameter("estado");

            recorridos recorrido = new recorridos();
            recorrido.setId_bus(idBus);
            recorrido.setId_ruta(idRuta);
            recorrido.setId_conductor(idConductor);
            recorrido.setFecha(fecha);
            recorrido.setHora_inicio(horaInicio);
            recorrido.setHora_fin(horaFin);
            recorrido.setEstado(estado);


            boolean resultado;

            if (idParam != null && !idParam.isEmpty()) {
                recorrido.setId_recorrido(Integer.parseInt(idParam));
                resultado = recorridoDAO.updateRecorrido(recorrido);
            } else {
                resultado = recorridoDAO.insertRecorrido(recorrido);
            }

            if (resultado) {
                resp.sendRedirect(req.getContextPath() + "/recorridos");
            } else {
                req.setAttribute("error", "Error al guardar el recorrido");
                req.getRequestDispatcher("/views/admin/recorridos/formulario.jsp").forward(req, resp);
            }
        }
    }
}
