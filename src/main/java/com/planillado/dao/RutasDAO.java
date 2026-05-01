package com.planillado.dao;

import com.planillado.model.Rutas;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutasDAO {

    // Obtener todas las rutas
    public List<Rutas> getAllRutas() {
        List<Rutas> listaRutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Rutas ruta = new Rutas();
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

    // Obtener por ID
    public Rutas getRutaById(int id) {
        Rutas ruta = null;
        String sql = "SELECT * FROM rutas WHERE id_ruta = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ruta = new Rutas();
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

    // Buscar por origen
    public List<Rutas> getRutasByOrigen(String origen) {
        List<Rutas> lista = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE origen LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + origen + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Rutas ruta = new Rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                lista.add(ruta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Buscar por destino
    public List<Rutas> getRutasByDestino(String destino) {
        List<Rutas> lista = new ArrayList<>();
        String sql = "SELECT * FROM rutas WHERE destino LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + destino + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Rutas ruta = new Rutas();
                ruta.setIdRuta(rs.getInt("id_ruta"));
                ruta.setNombreRuta(rs.getString("nombre_ruta"));
                ruta.setOrigen(rs.getString("origen"));
                ruta.setDestino(rs.getString("destino"));
                ruta.setDuracionEstimada(rs.getInt("distancia_km"));
                lista.add(ruta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Insertar
    public boolean insertRuta(Rutas ruta) {
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

    // Actualizar
    public boolean updateRuta(Rutas ruta) {
        String sql = "UPDATE rutas SET nombre_ruta=?, origen=?, destino=?, distancia_km=? WHERE id_ruta=?";

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

    // Eliminar
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

    // Verificar existencia
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
}