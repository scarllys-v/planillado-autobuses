package com.planillado.servlets;

import com.planillado.dao.RolDAO;
import com.planillado.model.Roles;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/roles/*")
public class RolServlet extends HttpServlet {

    private RolDAO rolDAO;

    @Override
    public void init() {
        rolDAO = new RolDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action == null || action.equals("/")) {

            List<Roles> listaRoles = rolDAO.getAllRoles();
            req.setAttribute("roles", listaRoles);
            req.getRequestDispatcher("/views/admin/roles/listar.jsp").forward(req, resp);

        } else if (action.equals("/nuevo")) {

            req.getRequestDispatcher("/views/admin/roles/formulario.jsp").forward(req, resp);

        } else if (action.equals("/editar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Roles rol = rolDAO.getRolById(id);
            req.setAttribute("rol", rol);
            req.getRequestDispatcher("/views/admin/roles/formulario.jsp").forward(req, resp);

        } else if (action.equals("/eliminar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            rolDAO.deleteRol(id);
            resp.sendRedirect(req.getContextPath() + "/roles");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getPathInfo();

        if (action.equals("/guardar")) {

            String idParam = req.getParameter("id");
            String nombreRol = req.getParameter("nombre_rol");

            Roles rol = new Roles();
            rol.setNombreRol(nombreRol);

            boolean resultado;

            if (idParam != null && !idParam.isEmpty()) {

                rol.setIdRol(Integer.parseInt(idParam));
                resultado = rolDAO.updateRol(rol);

            } else {

                if (rolDAO.existeRol(nombreRol)) {
                    req.setAttribute("error", "El rol ya existe");
                    req.getRequestDispatcher("/views/admin/roles/formulario.jsp").forward(req, resp);
                    return;
                }

                resultado = rolDAO.insertRol(rol);
            }

            if (resultado) {
                resp.sendRedirect(req.getContextPath() + "/roles");
            } else {
                req.setAttribute("error", "Error al guardar el rol");
                req.getRequestDispatcher("/views/admin/roles/formulario.jsp").forward(req, resp);
            }
        }
    }
}