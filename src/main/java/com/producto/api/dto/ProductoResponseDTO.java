package com.producto.api.dto;


import com.producto.api.modeloBase.ResponseGenericoDTO;

import java.util.List;

public class ProductoResponseDTO extends ResponseGenericoDTO {

	private List<ProductoDTO> producto;

	public ProductoResponseDTO() {
	}


	public ProductoResponseDTO(List<ProductoDTO> producto) {
		this.producto = producto;
	}


	public List<ProductoDTO> getProducto() {
		return producto;
	}

	public void setProducto(List<ProductoDTO> producto) {
		this.producto = producto;
	}

}
