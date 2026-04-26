package com.planillado.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Datos de conexión a NeonDB
    private static final String URL = "jdbc:postgresql://ep-withered-forest-aiu3dcl6-pooler.c-4.us-east-1.aws.neon.tech:5432/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_VXw04BUGlrjq";

    // Driver de PostgreSQL
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" Driver de PostgreSQL no encontrado");
            e.printStackTrace();
        }
    }

    private DatabaseConnection() {}

    // Este método SIEMPRE crea una NUEVA conexión activa
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println(" Nueva conexión establecida");
        return conn;
    }

    // Método para cerrar una conexión
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println(" Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    // Método para probar la conexión
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println(" Fallo en la prueba de conexión: " + e.getMessage());
            return false;
        }
    }
}