package com.planillado.model;

import java.sql.Timestamp;

public class Eventos_recorrido {

    private int id_evento;
    private int id_recorrido;
    private Integer id_parada; // ✅ CORREGIDO (permite null)
    private int id_usuario;
    private String tipo_evento;
    private Timestamp hora_registro;
    private Integer tiempo_estimado_siguiente;
    private String observacion;

    public Eventos_recorrido() {}

    public Eventos_recorrido(int id_evento, int id_recorrido, Integer id_parada, int id_usuario,
                             String tipo_evento, Timestamp hora_registro,
                             Integer tiempo_estimado_siguiente, String observacion) {
        this.id_evento = id_evento;
        this.id_recorrido = id_recorrido;
        this.id_parada = id_parada;
        this.id_usuario = id_usuario;
        this.tipo_evento = tipo_evento;
        this.hora_registro = hora_registro;
        this.tiempo_estimado_siguiente = tiempo_estimado_siguiente;
        this.observacion = observacion;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getId_recorrido() {
        return id_recorrido;
    }

    public void setId_recorrido(int id_recorrido) {
        this.id_recorrido = id_recorrido;
    }

    public Integer getId_parada() {
        return id_parada;
    }

    public void setId_parada(Integer id_parada) {
        this.id_parada = id_parada;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_evento() {
        return tipo_evento;
    }

    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }

    public Timestamp getHora_registro() {
        return hora_registro;
    }

    public void setHora_registro(Timestamp hora_registro) {
        this.hora_registro = hora_registro;
    }

    public Integer getTiempo_estimado_siguiente() {
        return tiempo_estimado_siguiente;
    }

    public void setTiempo_estimado_siguiente(Integer tiempo_estimado_siguiente) {
        this.tiempo_estimado_siguiente = tiempo_estimado_siguiente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Eventos_recorrido{" +
                "id_evento=" + id_evento +
                ", id_recorrido=" + id_recorrido +
                ", id_parada=" + id_parada +
                ", id_usuario=" + id_usuario +
                ", tipo_evento='" + tipo_evento + '\'' +
                ", hora_registro=" + hora_registro +
                ", tiempo_estimado_siguiente=" + tiempo_estimado_siguiente +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}