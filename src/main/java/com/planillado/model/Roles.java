package com.planillado.model;

public class Roles {
    private int id_rol;
    private String nombre_rol;

    // Constructor vacío
    public Roles() {}

    // Constructor con parámetros
    public Roles(int id_rol, String nombre_rol) {
        this.id_rol = id_rol;
        this.nombre_rol = nombre_rol;
    }

    // Getters y Setters
    public int getIdRol() {
        return id_rol;
    }

    public void setIdRol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombreRol() {
        return nombre_rol;
    }

    public void setNombreRol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRol=" + id_rol +
                ", nombreRol='" + nombre_rol + '\'' +
                '}';
    }
}
