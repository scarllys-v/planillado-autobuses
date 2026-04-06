package com.planillado.servlets;

import com.planillado.dao.UsuarioDAO;
import com.planillado.model.usuarios;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@SuppressWarnings("CallToPrintStackTrace")

@WebServlet("/registro")
public class registroServlet extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        try {
            usuarios usuarioExistente = usuarioDAO.obtenerUsuarioPorEmail(email);

            if (usuarioExistente != null) {
                response.sendRedirect("views/registro.html?error=El email ya está registrado");
                return;
            }

            usuarios nuevoUsuario = new usuarios();
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

            response.sendRedirect("views/login.html?success=Cuenta creada exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("views/registro.html?error=Error al crear la cuenta");
        }
    }
}
