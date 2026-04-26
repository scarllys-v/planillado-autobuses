package com.planillado.model;

public class buses {
    private int id_bus;
    private String numero_bus;
    private String placa;
    private String modelo;
    private String estado;

    // Constructor vacío
    public buses() {}

    // Constructor completo
    public buses(int id_bus,String numero_bus, String placa, String modelo,  String estado) {
        this.id_bus = id_bus;
        this.numero_bus = numero_bus;
        this.placa = placa;
        this.modelo = modelo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdBus() {
        return id_bus;
    }

    public void setIdBus(int id_bus) {
        this.id_bus = id_bus;
    }

    public String getNumeroBus() {
        return numero_bus;
    }

    public void setNumeroBus(String numero_bus) {
        this.numero_bus = numero_bus;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }



    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "idBus=" + id_bus +
                ", numero de bus='" + numero_bus + '\'' +
                ", placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
