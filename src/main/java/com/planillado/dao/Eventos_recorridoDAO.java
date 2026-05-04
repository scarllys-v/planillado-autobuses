package com.planillado.dao;

import com.planillado.model.Eventos_recorrido;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Eventos_recorridoDAO {

    // 🔹 INSERTAR EVENTO
    public boolean insertar(Eventos_recorrido evento) {

        String sql = "INSERT INTO eventos_recorrido " +
                "(id_recorrido, id_parada, id_usuario, tipo_evento, hora_registro, tiempo_estimado_siguiente, observacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, evento.getId_recorrido());

            // Manejo de NULL en parada
            if (evento.getId_parada() != null) {
                ps.setInt(2, evento.getId_parada());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, evento.getId_usuario());
            ps.setString(4, evento.getTipo_evento());
            ps.setTimestamp(5, evento.getHora_registro());

            if (evento.getTiempo_estimado_siguiente() != null) {
                ps.setInt(6, evento.getTiempo_estimado_siguiente());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setString(7, evento.getObservacion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 LISTAR POR RECORRIDO
    public List<Eventos_recorrido> listarPorRecorrido(int idRecorrido) {

        List<Eventos_recorrido> lista = new ArrayList<>();

        String sql = "SELECT * FROM eventos_recorrido WHERE id_recorrido = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRecorrido);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Eventos_recorrido e = new Eventos_recorrido();

                e.setId_evento(rs.getInt("id_evento"));
                e.setId_recorrido(rs.getInt("id_recorrido"));

                // Manejo de NULL
                if (rs.getObject("id_parada") != null) {
                    e.setId_parada(rs.getInt("id_parada"));
                } else {
                    e.setId_parada(null);
                }

                e.setId_usuario(rs.getInt("id_usuario"));
                e.setTipo_evento(rs.getString("tipo_evento"));
                e.setHora_registro(rs.getTimestamp("hora_registro"));

                if (rs.getObject("tiempo_estimado_siguiente") != null) {
                    e.setTiempo_estimado_siguiente(rs.getInt("tiempo_estimado_siguiente"));
                } else {
                    e.setTiempo_estimado_siguiente(null);
                }

                e.setObservacion(rs.getString("observacion"));

                lista.add(e);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}