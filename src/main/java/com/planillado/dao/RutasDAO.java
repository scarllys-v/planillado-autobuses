package com.planillado.dao;

import com.planillado.model.rutas;
import com.planillado.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutasDAO {
    // Obtener todas las rutas
    public List<rutas> getAllRutas() {
        List<rutas> listaRutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rutas ruta = new rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                listaRutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutas;
    }

    // Obtener ruta por ID
    public rutas getRutaById(int id) {
        rutas ruta = null;
        String sql = "SELECT * FROM rutas WHERE id_ruta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ruta = new rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruta;
    }

    // Buscar rutas por origen
    public List<rutas> getRutasByOrigen(String origen) {
        List<rutas> listaRutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE origen LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + origen + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rutas ruta = new rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                listaRutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutas;
    }

    // Buscar rutas por destino
    public List<rutas> getRutasByDestino(String destino) {
        List<rutas> listaRutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE destino LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + destino + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rutas ruta = new rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                listaRutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutas;
    }

    // Insertar nueva ruta
    public boolean insertRuta(rutas ruta) {
        String sql = "INSERT INTO rutas (nombre_ruta, origen, destino, distancia_km) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ruta.getNombreRuta());
            pstmt.setString(2, ruta.getOrigen());
            pstmt.setString(3, ruta.getDestino());
            pstmt.setInt(4, ruta.getDuracionEstimada());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar ruta
    public boolean updateRuta(rutas ruta) {
        String sql = "UPDATE rutas SET nombre_ruta = ?, origen = ?, destino = ?, distancia_km = ? WHERE id_ruta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ruta.getNombreRuta());
            pstmt.setString(2, ruta.getOrigen());
            pstmt.setString(3, ruta.getDestino());
            pstmt.setInt(4, ruta.getDuracionEstimada());
            pstmt.setInt(5, ruta.getIdRuta());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar ruta
    public boolean deleteRuta(int id) {
        String sql = "DELETE FROM rutas WHERE id_ruta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si existe una ruta por nombre
    public boolean existeRuta(String nombreRuta) {
        String sql = "SELECT COUNT(*) FROM rutas WHERE nombre_ruta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreRuta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtener rutas con distancia menor a cierto valor
    public List<rutas> getRutasByDistanciaMax(int distanciaMax) {
        List<rutas> listaRutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE distancia_km <= ? ORDER BY distancia_km ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, distanciaMax);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                rutas ruta = new rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                listaRutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRutas;
    }
}
