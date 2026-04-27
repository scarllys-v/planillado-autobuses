package com.planillado.dao;

import com.planillado.model.roles;
import com.planillado.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {

    // Obtener todos los roles
    public List<roles> getAllRoles() {
        List<roles> listaRoles = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                roles rol = new roles();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
                listaRoles.add(rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRoles;
    }

    // Obtener rol por ID
    public roles getRolById(int id) {
        roles rol = null;
        String sql = "SELECT * FROM roles WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rol = new roles();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

    // Obtener rol por nombre
    public roles getRolByNombre(String nombre) {
        roles rol = null;
        String sql = "SELECT * FROM roles WHERE nombre_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                rol = new roles();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombreRol(rs.getString("nombre_rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

    // Insertar nuevo rol
    public boolean insertRol(roles rol) {
        String sql = "INSERT INTO roles (nombre_rol) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, rol.getNombreRol());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar rol
    public boolean updateRol(roles rol) {
        String sql = "UPDATE roles SET nombre_rol = ? WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, rol.getNombreRol());
            pstmt.setInt(2, rol.getIdRol());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar rol
    public boolean deleteRol(int id) {
        String sql = "DELETE FROM roles WHERE id_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si existe un rol por nombre
    public boolean existeRol(String nombre) {
        String sql = "SELECT COUNT(*) FROM roles WHERE nombre_rol = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
