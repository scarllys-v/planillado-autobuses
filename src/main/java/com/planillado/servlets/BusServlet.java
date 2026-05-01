package com.planillado.servlets;

import com.planillado.utils.DatabaseConnection;
import com.planillado.model.Buses;
import com.planillado.dao.BusDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bus")
public class BusServlet extends HttpServlet{

    private BusDAO busDAO = new BusDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            List<Buses> lista = busDAO.listarBuses();
            request.setAttribute("buses", lista);

            request.getRequestDispatcher("/views/admin/buses.jsp")
                    .forward(request, response);
        }catch (SQLException e){
            e.printStackTrace();
            response.getWriter().println("Error al listar buses");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numero = request.getParameter("numero_bus");
        String placa = request.getParameter("placa");
        String modelo = request.getParameter("modelo");
        String estado = request.getParameter("estado");

        Buses bus = new Buses();
        bus.setNumeroBus(numero);
        bus.setPlaca(placa);
        bus.setModelo(modelo);;
        bus.setEstado(estado);

        try{
            busDAO.insertarBus(bus);
            response.sendRedirect(request.getContextPath()+"/bus");
        }catch (SQLException e){
            e.printStackTrace();
            response.getWriter().println("Error al guardar bus");
        }
    }
}
