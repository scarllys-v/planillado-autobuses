package com.planillado.dao;

import com.planillado.model.recorridos;
import com.planillado.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecorridoDAO {

    // Obtener todos los recorridos
    public List<recorridos> getAllRecorridos() {
        List<recorridos> recorridos = new ArrayList<>();
        String sql = "SELECT * FROM recorridos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                recorridos r = new recorridos();
                r.setId_recorrido(rs.getInt("id_recorrido"));
                r.setId_bus(rs.getInt("id_bus"));
                r.setId_ruta(rs.getInt("id_ruta"));
                r.setId_conductor(rs.getInt("id_conductor"));
                r.setFecha(rs.getDate("fecha"));
                r.setHora_inicio(rs.getTime("hora_inicio"));
                r.setHora_fin(rs.getTime("hora_fin"));
                r.setEstado(rs.getString("estado"));
                recorridos.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recorridos;
    }

    // Obtener recorrido por ID
    public recorridos getRecorridoById(int id) {
        recorridos recorrido = null;
        String sql = "SELECT * FROM recorridos WHERE id_recorrido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                recorrido = new recorridos();
                recorrido.setId_recorrido(rs.getInt("id_recorrido"));
                recorrido.setId_bus(rs.getInt("id_bus"));
                recorrido.setId_ruta(rs.getInt("id_ruta"));
                recorrido.setId_conductor(rs.getInt("id_conductor"));
                recorrido.setFecha(rs.getDate("fecha"));
                recorrido.setHora_inicio(rs.getTime("hora_inicio"));
                recorrido.setHora_fin(rs.getTime("hora_fin"));
                recorrido.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recorrido;
    }

    // Obtener recorridos por fecha
    public List<recorridos> getRecorridosByFecha(Date fecha) {
        List<recorridos> recorridos = new ArrayList<>();
        String sql = "SELECT * FROM recorridos WHERE fecha = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDate(1, fecha);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                recorridos r = new recorridos();
                r.setId_recorrido(rs.getInt("id_recorrido"));
                r.setId_bus(rs.getInt("id_bus"));
                r.setId_ruta(rs.getInt("id_ruta"));
                r.setId_conductor(rs.getInt("id_conductor"));
                r.setFecha(rs.getDate("fecha"));
                r.setHora_inicio(rs.getTime("hora_inicio"));
                r.setHora_fin(rs.getTime("hora_fin"));
                r.setEstado(rs.getString("estado"));
                recorridos.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recorridos;
    }

    // Insertar nuevo recorrido
    public boolean insertRecorrido(recorridos recorrido) {
        String sql = "INSERT INTO recorridos (id_bus, id_ruta, id_conductor, fecha, hora_inicio, hora_fin, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recorrido.getId_bus());
            pstmt.setInt(2, recorrido.getId_ruta());
            pstmt.setInt(3, recorrido.getId_conductor());
            pstmt.setDate(4, recorrido.getFecha());
            pstmt.setTime(5, recorrido.getHora_inicio());
            pstmt.setTime(6, recorrido.getHora_fin());
            pstmt.setString(7, recorrido.getEstado());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar recorrido
    public boolean updateRecorrido(recorridos recorrido) {
        String sql = "UPDATE recorridos SET id_bus=?, id_ruta=?, id_conductor=?, fecha=?, "
                + "hora_inicio=?, hora_fin=?, estado=? WHERE id_recorrido=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, recorrido.getId_bus());
            pstmt.setInt(2, recorrido.getId_ruta());
            pstmt.setInt(3, recorrido.getId_conductor());
            pstmt.setDate(4, recorrido.getFecha());
            pstmt.setTime(5, recorrido.getHora_inicio());
            pstmt.setTime(6, recorrido.getHora_fin());
            pstmt.setString(7, recorrido.getEstado());
            pstmt.setInt(8, recorrido.getId_recorrido());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar recorrido
    public boolean deleteRecorrido(int id) {
        String sql = "DELETE FROM recorridos WHERE id_recorrido=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener recorridos por conductor
    public List<recorridos> getRecorridosByConductor(int idConductor) {
        List<recorridos> recorridos = new ArrayList<>();
        String sql = "SELECT * FROM recorridos WHERE id_conductor = ? ORDER BY fecha DESC, hora_inicio DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idConductor);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                recorridos r = new recorridos();
                r.setId_recorrido(rs.getInt("id_recorrido"));
                r.setId_bus(rs.getInt("id_bus"));
                r.setId_ruta(rs.getInt("id_ruta"));
                r.setId_conductor(rs.getInt("id_conductor"));
                r.setFecha(rs.getDate("fecha"));
                r.setHora_inicio(rs.getTime("hora_inicio"));
                r.setHora_fin(rs.getTime("hora_fin"));
                r.setEstado(rs.getString("estado"));
                recorridos.add(r);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recorridos;
    }
}
