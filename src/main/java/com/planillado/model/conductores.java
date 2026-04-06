package com.planillado.model;

public class conductores {
    private int id_conductor;
    private String nombre;
    private String licencia;
    private String telefono;


    // Constructor vacío
    public conductores() {}

    // Constructor completo
    public conductores(int id_conductor, String nombre, String licencia, String telefono) {
        this.id_conductor = id_conductor;
        this.nombre = nombre;
        this.licencia = licencia;

    }

    // Getters y Setters
    public int getIdConductor() {
        return id_conductor;
    }

    public void setIdConductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Conductor{" +
                "idConductor=" + id_conductor +
                ", nombre='" + nombre + '\'' +
                ", licencia='" + licencia + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
