package com.planillado.servlets;

import com.planillado.dao.Eventos_recorridoDAO;
import com.planillado.model.Eventos_recorrido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/eventos")
public class Eventos_recorridoServlet extends HttpServlet {

    private Eventos_recorridoDAO dao = new Eventos_recorridoDAO();

    // 🔹 LISTAR EVENTOS
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // puedes filtrar por recorrido si quieres
        String idRecorridoStr = request.getParameter("id_recorrido");

        if (idRecorridoStr != null) {
            int idRecorrido = Integer.parseInt(idRecorridoStr);
            List<Eventos_recorrido> lista = dao.listarPorRecorrido(idRecorrido);
            request.setAttribute("eventos", lista);
        }

        request.getRequestDispatcher("/views/admin/eventos/listar.jsp")
                .forward(request, response);
    }

    // 🔹 CREAR EVENTO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Eventos_recorrido evento = new Eventos_recorrido();

        evento.setId_recorrido(Integer.parseInt(request.getParameter("id_recorrido")));

        // Manejo de NULL en parada
        String paradaStr = request.getParameter("id_parada");
        if (paradaStr != null && !paradaStr.isEmpty()) {
            evento.setId_parada(Integer.parseInt(paradaStr));
        } else {
            evento.setId_parada(null);
        }

        evento.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
        evento.setTipo_evento(request.getParameter("tipo_evento"));

        // Hora automática
        evento.setHora_registro(new Timestamp(System.currentTimeMillis()));

        String tiempoStr = request.getParameter("tiempo_estimado_siguiente");
        if (tiempoStr != null && !tiempoStr.isEmpty()) {
            evento.setTiempo_estimado_siguiente(Integer.parseInt(tiempoStr));
        } else {
            evento.setTiempo_estimado_siguiente(null);
        }

        evento.setObservacion(request.getParameter("observacion"));

        dao.insertar(evento);

        // redirección igual que conductores
        response.sendRedirect(request.getContextPath() + "/eventos");
    }
}