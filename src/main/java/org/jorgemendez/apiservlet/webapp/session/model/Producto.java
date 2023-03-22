package org.jorgemendez.apiservlet.webapp.session.model;

import java.time.LocalDate;

public class Producto {
    private Long id;
    private String nombre;
    private Categoria categoria;
    private Integer precio;

    private String sku;
    private LocalDate fecha;

    public Producto() {
    }

    public Producto(Long id, String nombre,  String tipo, Integer precio) {
        this.id = id;
        this.nombre = nombre;
        Categoria c = new Categoria();
        c.setNombre(tipo);
        this.categoria = c;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
