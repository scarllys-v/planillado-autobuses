package com.planillado.dao;

import com.planillado.model.Paradas;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParadasDAO {

    public List<Paradas> getAllParadas() {
        List<Paradas> lista = new ArrayList<>();
        String sql = "SELECT * FROM paradas";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paradas p = new Paradas();
                p.setIdParada(rs.getInt("id_parada"));
                p.setIdRuta(rs.getInt("id_ruta"));
                p.setNombre(rs.getString("nombre_parada"));
                p.setOrden(rs.getInt("orden"));
                p.setLatitud(rs.getBigDecimal("latitud"));
                p.setLongitud(rs.getBigDecimal("longitud"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean insertParada(Paradas p) {
        String sql = "INSERT INTO paradas (id_ruta, nombre_parada, orden, latitud, longitud) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p.getIdRuta());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getOrden());
            ps.setBigDecimal(4, p.getLatitud());
            ps.setBigDecimal(5, p.getLongitud());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}