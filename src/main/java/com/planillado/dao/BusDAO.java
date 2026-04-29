package com.planillado.dao;

import com.planillado.model.Buses;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//holis
public class BusDAO {
    public void insertarBus(Buses bus) throws SQLException {
        String sql = "INSERT INTO buses (numero_bus, placa, model, estado) VALUES (?, ?, ?, ?)";

        try(Connection conn = com.planillado.utils.DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, bus.getNumeroBus());
            stmt.setString(2, bus.getPlaca());
            stmt.setString(3, bus.getModelo());
            stmt.setString(4, bus.getEstado());

            stmt.executeUpdate();
        }
    }
    public List<Buses> listarBuses() throws SQLException {
        List<Buses> lista = new ArrayList<>();
        String sql = "SELECT * FROM buses";

        try(Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Buses bus = new Buses();
                bus.setIdBus(rs.getInt("id_bus"));
                bus.setNumeroBus(rs.getString("numero_bus"));
                bus.setPlaca(rs.getString("placa"));
                bus.setModelo(rs.getString("model"));
                bus.setEstado(rs.getString("estado"));

                lista.add(bus);
            }
        }
        return lista;
    }
    public Buses buscarBus(int id) throws SQLException{
        String sql = "SELECT * FROM buses WHERE id_bus = ?";
        Buses bus = null;

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                bus = new Buses();
                bus.setIdBus(rs.getInt("id_bus"));
                bus.setNumeroBus(rs.getString("numero_bus"));
                bus.setPlaca(rs.getString("placa"));
                bus.setModelo(rs.getString("model"));
                bus.setEstado(rs.getString("estado"));
            }
        }
        return bus;
    }
    public void eliminarBus(int id)throws SQLException{
        String sql = "DELETE FROM buses WHERE id_bus = ?";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
