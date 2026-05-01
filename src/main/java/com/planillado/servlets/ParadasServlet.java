package com.planillado.servlets;

import com.planillado.dao.ParadasDAO;
import com.planillado.model.Paradas;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/paradas")
public class ParadasServlet extends HttpServlet {

    private ParadasDAO dao = new ParadasDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Paradas> lista = dao.getAllParadas();
        request.setAttribute("paradas", lista);

        request.getRequestDispatcher("/views/admin/paradas/listar.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Paradas p = new Paradas();
        p.setIdRuta(Integer.parseInt(request.getParameter("id_ruta")));
        p.setNombre(request.getParameter("nombre_parada"));
        p.setOrden(Integer.parseInt(request.getParameter("orden")));

        dao.insertParada(p);

        response.sendRedirect(request.getContextPath() + "/paradas");
    }
}