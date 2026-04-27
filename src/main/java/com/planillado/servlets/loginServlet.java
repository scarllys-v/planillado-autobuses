package com.planillado.servlets;

import com.planillado.dao.UsuarioDAO;
import com.planillado.model.usuarios;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(loginServlet.class.getName());
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().println("Servlet login funcionando correctamente");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validar campos vacíos
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/views/login.html?error=Email y contraseña son obligatorios");
            return;
        }

        try {
            usuarios usuario = usuarioDAO.obtenerUsuarioPorEmail(email);

            // Usuario no existe
            if (usuario == null) {
                response.sendRedirect(request.getContextPath() + "/views/login.html?error=Usuario no encontrado");
                return;
            }

            // Contraseña incorrecta
            if (!usuario.getPasswordHash().equals(password)) {
                response.sendRedirect(request.getContextPath() + "/views/login.html?error=Contraseña incorrecta");
                return;
            }

            // Usuario desactivado
            if (!usuario.isActivo()) {
                response.sendRedirect(request.getContextPath() + "/views/login.html?error=Usuario desactivado, contacte al administrador");
                return;
            }

            // Login exitoso
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("email", usuario.getEmail());
            session.setAttribute("idRol", usuario.getIdRol());

            int rol = usuario.getIdRol();

            // Redirigir según rol (¡con .html!)
            if (rol == 1) {
                response.sendRedirect(request.getContextPath() + "/views/admin/dashboard.html");
            } else if (rol == 2) {
                response.sendRedirect(request.getContextPath() + "/views/conductor/dashboard.html");
            } else {
                response.sendRedirect(request.getContextPath() + "/views/usuario/tracking.html");
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en login: " + e.getMessage(), e);
            response.sendRedirect(request.getContextPath() + "/views/login.html?error=Error interno del servidor");
        }
    }
}