package com.planillado.servlets;

import com.planillado.dao.UsuarioDAO;
import com.planillado.model.Usuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RegistroServlet.class.getName());

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        try {
            Usuarios usuarioExistente = usuarioDAO.obtenerUsuarioPorEmail(email);

            if (usuarioExistente != null) {
                response.sendRedirect(request.getContextPath() + "/views/registro.html?error=El email ya está registrado");
                return;
            }

            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPasswordHash(password);
            nuevoUsuario.setActivo(true);

            if ("conductor".equals(rol)) {
                nuevoUsuario.setIdRol(2);
            } else {
                nuevoUsuario.setIdRol(3);
            }

            usuarioDAO.insertarUsuario(nuevoUsuario);

            response.sendRedirect(request.getContextPath() + "/views/login.html?success=Cuenta creada exitosamente");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al registrar usuario con email: " + email, e);
            response.sendRedirect(request.getContextPath() + "/views/registro.html?error=Error interno del servidor");
        }
    }
}