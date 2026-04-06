package com.planillado;

import com.planillado.utils.DatabaseConnection;
import com.planillado.dao.UsuarioDAO;
import com.planillado.model.usuarios;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        System.out.println("=== Probando conexión a NeonDB ===");

        if (DatabaseConnection.testConnection()) {
            System.out.println(" Conexión directa exitosa\n");
        } else {
            System.err.println(" No se pudo conectar directamente\n");
        }

        // Probar el DAO
        System.out.println("=== Probando UsuarioDAO ===");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            var usuarios = usuarioDAO.obtenerTodosLosUsuarios();

            if (usuarios.isEmpty()) {
                System.out.println(" No hay usuarios en la base de datos");
            } else {
                System.out.println(" Se encontraron " + usuarios.size() + " usuarios:");
                for (usuarios u : usuarios) {
                    System.out.println("   - " + u.getNombre() + " (" + u.getEmail() + ")");
                }
            }

        } catch (SQLException e) {
            System.err.println(" Error al obtener usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }
}