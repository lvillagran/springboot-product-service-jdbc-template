package com.producto.api.dto;

import java.util.Date;

public class ProductoRequestDTO {

	private Long id;
    private String nombre;
	private Double precio;
	private String estado;
	private Date fechaResgistro;

	public ProductoRequestDTO()
	{}


	public ProductoRequestDTO(Long id, String nombre, Double precio, String estado, Date fechaResgistro) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.estado = estado;
		this.fechaResgistro = fechaResgistro;
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

	public Date getFechaResgistro() {
		return fechaResgistro;
	}

	public void setFechaResgistro(Date fechaResgistro) {
		this.fechaResgistro = fechaResgistro;
	}
}
