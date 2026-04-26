package com.planillado.dao;

import com.planillado.model.usuarios;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioDAO {
    // 1. INSERTAR un nuevo usuario
    public void insertarUsuario(usuarios usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email, password_hash, id_rol, activo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getPasswordHash());
            pstmt.setInt(4, usuario.getIdRol());
            pstmt.setBoolean(5, usuario.isActivo());

            pstmt.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                }
            }
        }
    }

    // 2. OBTENER usuario por ID
    public usuarios obtenerUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        usuarios usuario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }
        }
        return usuario;
    }

    // 3. OBTENER usuario por email
    public usuarios obtenerUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        usuarios usuario = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = mapearUsuario(rs);
            }
        }
        return usuario;
    }

    // 4. OBTENER TODOS los usuarios
    public List<usuarios> obtenerTodosLosUsuarios() throws SQLException {
        List<usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        } finally {
            // Cerrar recursos en orden inverso
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
        return usuarios;
    }

    // 6. OBTENER solo usuarios activos
    public List<usuarios> obtenerUsuariosActivos() throws SQLException {
        List<usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE activo = true";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }

    // 7. ACTUALIZAR un usuario
    public void actualizarUsuario(usuarios usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, password_hash = ?, id_rol = ?, activo = ? WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getPasswordHash());
            pstmt.setInt(4, usuario.getIdRol());
            pstmt.setBoolean(5, usuario.isActivo());
            pstmt.setInt(6, usuario.getIdUsuario());

            pstmt.executeUpdate();
        }
    }

    // 8. ACTUALIZAR solo el rol de un usuario
    public void actualizarRolUsuario(int idUsuario, int nuevoRol) throws SQLException {
        String sql = "UPDATE usuarios SET id_rol = ? WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nuevoRol);
            pstmt.setInt(2, idUsuario);
            pstmt.executeUpdate();
        }
    }

    // 9. DESACTIVAR usuario (borrado lógico)
    public void desactivarUsuario(int id) throws SQLException {
        String sql = "UPDATE usuarios SET activo = false WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // 10. ACTIVAR usuario
    public void activarUsuario(int id) throws SQLException {
        String sql = "UPDATE usuarios SET activo = true WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // 11. ELIMINAR físicamente un usuario (borrado permanente)
    public void eliminarUsuarioPermanente(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // 12. CONTAR cuántos usuarios hay
    public int contarUsuarios() throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    // Metodo de prueba para verificar conexión
    public void probarConexion() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println(" Conexión OK en UsuarioDAO");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println(" Error en prueba de conexión: " + e.getMessage());
        }
    }

    // Metodo auxiliar para mapear ResultSet a objeto usuarios
    private usuarios mapearUsuario(ResultSet rs) throws SQLException {
        usuarios usuario = new usuarios();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPasswordHash(rs.getString("password_hash"));
        usuario.setIdRol(rs.getInt("id_rol"));
        usuario.setActivo(rs.getBoolean("activo"));
        return usuario;
    }
}
