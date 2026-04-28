package com.planillado.servlets;

import com.planillado.dao.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            com.planillado.model.Usuarios usuario = usuarioDAO.obtenerUsuarioPorEmail(email);

            if (usuario != null && usuario.getPasswordHash().equals(password)) {

                if (!usuario.isActivo()) {
                    response.sendRedirect(request.getContextPath() + "/views/login.html?error=Usuario desactivado");
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setAttribute("idUsuario", usuario.getIdUsuario());
                session.setAttribute("nombre", usuario.getNombre());
                session.setAttribute("email", usuario.getEmail());
                session.setAttribute("idRol", usuario.getIdRol());

                int rol = usuario.getIdRol();

                if (rol == 1) {
                    response.sendRedirect(request.getContextPath() + "/views/admin/dashboard.html");
                } else if (rol == 2) {
                    response.sendRedirect(request.getContextPath() + "/views/conductor/dashboard.html");
                } else {
                    response.sendRedirect(request.getContextPath() + "/views/usuario/tracking.html");
                }

            } else {
                response.sendRedirect(request.getContextPath() + "/views/login.html?error=Email o contraseña incorrectos");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/login.html?error=Error al iniciar sesión");
        }
    }
}