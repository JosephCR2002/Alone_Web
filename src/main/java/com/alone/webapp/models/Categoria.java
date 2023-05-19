package com.alone.webapp.models;

public class Categoria {
    private Integer id;
    private String descripcion;
    private int genero;
    private String img;
    public Categoria() {
    }

    public Categoria(Integer id, String descripcion, int genero, String img) {
        this.id = id;
        this.descripcion = descripcion;
        this.genero = genero;
        this.img = img;
    }

    public Categoria(String descripcion, int genero, String img) {
        this.descripcion = descripcion;
        this.genero = genero;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
