package com.planillado.model;

import java.sql.Date;
import java.sql.Time;

public class Recorridos {
    private int id_recorrido;
    private int id_bus;
    private int id_ruta;
    private int id_conductor;
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;
    private String estado;

    // Constructor vacío
    public Recorridos() {}

    // Constructor con parámetros
    public Recorridos(int id_recorrido, int id_bus, int id_ruta, int id_conductor, Date fecha, Time hora_inicio, Time hora_fin, String estado) {
        this.id_recorrido = id_recorrido;
        this.id_bus = id_bus;
        this.id_ruta = id_ruta;
        this.id_conductor = id_conductor;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId_recorrido() {
        return id_recorrido;
    }

    public void setId_recorrido(int id_recorrido) {
        this.id_recorrido = id_recorrido;
    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Recorrido{" +
                "id_recorrido=" + id_recorrido +
                ", id_bus=" + id_bus +
                ", id_ruta=" + id_ruta +
                ", id_conductor=" + id_conductor +
                ", fecha=" + fecha +
                ", hora_inicio=" + hora_inicio +
                ", hora_fin=" + hora_fin +
                ", estado='" + estado + '\'' +
                '}';
    }
}
