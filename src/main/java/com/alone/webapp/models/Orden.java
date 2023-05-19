package com.alone.webapp.models;

import java.time.LocalDateTime;

public class Orden {
    private Integer id;
    private Usuario usuario;
    private Producto producto;
    private DetalleOrden detalleOrden;
    private LocalDateTime fechaCreacion;

    public Orden() {
    }

    public Orden(Integer id, Usuario usuario, Producto producto, DetalleOrden detalleOrden, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuario = usuario;
        this.producto = producto;
        this.detalleOrden = detalleOrden;
        this.fechaCreacion = fechaCreacion;
    }

    public Orden(Usuario usuario, Producto producto, DetalleOrden detalleOrden, LocalDateTime fechaCreacion) {
        this.usuario = usuario;
        this.producto = producto;
        this.detalleOrden = detalleOrden;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }
}
