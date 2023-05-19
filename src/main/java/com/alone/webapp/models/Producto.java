package com.alone.webapp.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Producto {
    private Integer id;
    private BigDecimal precio;
    private String descripcion;
    private Categoria categoria;
    private String imagen;
    private int estado;
    private int inventario;
    private LocalDateTime fechaCreacion;

    public Producto() {
    }

    public Producto(Integer id, BigDecimal precio, String descripcion, Categoria categoria, String imagen, int estado, int inventario, LocalDateTime fechaCreacion) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen = imagen;
        this.estado = estado;
        this.inventario = inventario;
        this.fechaCreacion = fechaCreacion;
    }

    public Producto(BigDecimal precio, String descripcion, Categoria categoria, String imagen, int estado, int inventario) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.imagen = imagen;
        this.estado = estado;
        this.inventario = inventario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
