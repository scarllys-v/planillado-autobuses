package com.planillado.servlets;

import com.planillado.dao.ConductoresDAO;
import com.planillado.model.Conductores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/conductores")
public class ConductoresServlet extends HttpServlet {

    private ConductoresDAO dao = new ConductoresDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // LISTAR conductores
        List<Conductores> lista = dao.listarConductores();
        request.setAttribute("conductores", lista);

        request.getRequestDispatcher("/views/admin/conductores/listar.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // CREAR conductor
        Conductores c = new Conductores();
        c.setNombre(request.getParameter("nombre"));
        c.setLicencia(request.getParameter("licencia"));
        c.setTelefono(request.getParameter("telefono"));

        dao.insertarConductor(c);

        response.sendRedirect(request.getContextPath() + "/conductores");
    }
}