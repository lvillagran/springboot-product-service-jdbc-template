package com.producto.api.model;

import java.util.Date;

public class Producto {

private Long id;
private String nombre;
private  Double precio;
private  String estado;

private Date fechaRegistro;


    private Date fechaActualizacion;

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }



/** Constructor sin parametros */
    public Producto()
    { }

    /** Constructor con parametros */
    public Producto(Long id, String nombre, Double precio, String estado, Date fechaRegistro, Date fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
