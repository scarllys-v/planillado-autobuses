package com.planillado.dao;

import com.planillado.model.Recorridos;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecorridoDAO {

    // Obtener todos los recorridos
    public List<Recorridos> getAllRecorridos() {
        List<Recorridos> lista = new ArrayList<>();
        String sql = "SELECT * FROM recorridos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Recorridos r = new Recorridos();
                r.setId_recorrido(rs.getInt("id_recorrido"));
                r.setId_bus(rs.getInt("id_bus"));
                r.setId_ruta(rs.getInt("id_ruta"));
                r.setId_conductor(rs.getInt("id_conductor"));
                r.setFecha(rs.getDate("fecha"));
                r.setHora_inicio(rs.getTime("hora_inicio"));
                r.setHora_fin(rs.getTime("hora_fin"));
                r.setEstado(rs.getString("estado"));
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Obtener por ID
    public Recorridos getRecorridoById(int id) {
        Recorridos recorrido = null;
        String sql = "SELECT * FROM recorridos WHERE id_recorrido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                recorrido = new Recorridos();
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

    // Insertar
    public boolean insertRecorrido(Recorridos recorrido) {
        String sql = "INSERT INTO recorridos (id_bus, id_ruta, id_conductor, fecha, hora_inicio, hora_fin, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

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

    // Actualizar
    public boolean updateRecorrido(Recorridos recorrido) {
        String sql = "UPDATE recorridos SET id_bus=?, id_ruta=?, id_conductor=?, fecha=?, hora_inicio=?, hora_fin=?, estado=? WHERE id_recorrido=?";

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

    // Eliminar
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
}