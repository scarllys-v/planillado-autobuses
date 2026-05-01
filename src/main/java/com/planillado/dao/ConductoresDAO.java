package com.planillado.dao;

import com.planillado.model.Conductores;
import com.planillado.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConductoresDAO {
    public List<Conductores> listarConductores(){
        List<Conductores> lista = new ArrayList<>();
        String sql = "SELECT * FROM conductores";

        try(Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                Conductores c = new Conductores();
                c.setIdConductor(rs.getInt("id_conductor"));
                c.setNombre(rs.getString("nombre"));
                c.setLicencia(rs.getString("licencia"));
                c.setTelefono(rs.getString("telefono"));

                lista.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    public void insertarConductor(Conductores c){
        String sql = "INSERT INTO conductores (nombre, licencia, telefono) VALUES (?,?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getLicencia());
            ps.setString(3, c.getTelefono());

            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
