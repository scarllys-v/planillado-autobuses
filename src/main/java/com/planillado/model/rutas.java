package com.planillado.model;

public class rutas {
    private int id_ruta;
    private String nombre_ruta;
    private String origen;
    private String destino;
    private int distancia_km;

    // Constructor vacío
    public rutas() {}

    // Constructor completo
    public rutas(int id_ruta, String nombre_ruta, String origen, String destino, int distancia_km) {
        this.id_ruta = id_ruta;
        this.nombre_ruta = nombre_ruta;
        this.origen = origen;
        this.destino = destino;
        this.distancia_km = distancia_km;
    }

    // Getters y Setters
    public int getIdRuta() {
        return id_ruta;
    }

    public void setIdRuta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getNombreRuta() {
        return nombre_ruta;
    }

    public void setNombreRuta(String nombre_ruta) {
        this.nombre_ruta = nombre_ruta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDuracionEstimada() {
        return distancia_km;
    }

    public void setDuracionEstimada(int distancia_km) {
        this.distancia_km = distancia_km;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "idRuta=" + id_ruta +
                ", nombreRuta='" + nombre_ruta + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", duracionEstimada=" + distancia_km +
                '}';
    }
}
