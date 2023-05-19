package com.alone.webapp.models;

import java.time.LocalDateTime;

public class Usuario {
    //Atributos
    private Integer id;
    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private int nivel;
    private LocalDateTime fechaCreacion;
    
    //Constructores

    public Usuario(Integer id, String nombre, String telefono, String email, String password, int nivel, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.nivel = nivel;
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario(String nombre, String telefono, String email, String password, int nivel,LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.nivel = nivel;
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario() {
    }
    
    //Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
