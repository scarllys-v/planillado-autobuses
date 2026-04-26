package com.planillado.model;
import java.math.BigDecimal;

public class paradas {
    private int id_parada;
    private int id_ruta;
    private String nombre_parada;
    private int orden;
    private BigDecimal latitud;
    private BigDecimal longitud;


    // Constructor vacío
    public paradas() {}

    // Constructor completo
    public paradas(int id_parada,int id_ruta, String nombre_parada,int orden, BigDecimal latitud, BigDecimal longitud) {
        this.id_parada = id_parada;
        this.id_ruta = id_ruta;
        this.nombre_parada = nombre_parada;
        this.orden = orden;
        this.latitud = latitud;
        this.longitud = longitud;

    }

    // Getters y Setters
    public int getIdParada() {
        return id_parada;
    }

    public void setIdParada(int id_parada) {
        this.id_parada = id_parada;
    }

    public int getIdRuta() {
        return id_ruta;
    }

    public void setIdRuta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getNombre() {
        return nombre_parada;
    }

    public void setNombre(String nombre_parada) {
        this.nombre_parada = nombre_parada;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "Parada{" +
                "idParada=" + id_parada +
                "idParada=" + id_ruta +
                ", nombre='" + nombre_parada + '\'' +
                ", orden=" + orden +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
