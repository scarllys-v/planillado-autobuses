package com.planillado.model;


public class Usuarios {
    private int id_usuario;
    private String nombre;
    private String email;
    private String password_hash;
    private int id_rol;
    private boolean activo;


    // Constructor vacío
    public Usuarios() {}

    public Usuarios(int id_usuario, String nombre, String email, String password_hash, int id_rol, boolean activo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.email = email;
        this.password_hash = password_hash;
        this.id_rol = id_rol;
        this.activo = activo;
    }



    // Getters y Setters
    public int getIdUsuario() {
        return id_usuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }

    public int getIdRol() {
        return id_rol;
    }

    public void setIdRol(int id_rol) {
        this.id_rol = id_rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + id_usuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", idRol=" + id_rol +
                ", activo=" + activo +
                '}';
    }
}
