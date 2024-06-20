package com.producto.api.service;

import com.producto.api.dto.ProductoDTO;
import com.producto.api.model.Producto;
import com.producto.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    /** Registra producto */
    public void registrar(Producto producto) {
        productoRepository.registrar(producto);
    }


    /** Devuelve lista todos los productos */
    public List<ProductoDTO> listaTodosProductos() {
        List<Producto> resultObj =  productoRepository.listaTodosProductos();
        return convertirProductoDTOAll(resultObj);
    }


    /** Devuelve lista productos por Id */
    public List<ProductoDTO> consultaProductosPorId(Long id) {
        List<Producto> resultObj = productoRepository.productosById(id);
        return convertirProductoDTOPorId(resultObj);
    }

    /** Actualiza producto por Id */
    public void actualizarProducto(Producto producto){
        productoRepository.actualizaPrecioById(producto);
    }

    /** Eliminar */
    public void deleteProducto(Producto producto){
        productoRepository.eliminarProductoById(producto);
    }


    /** Combierte de List<Object[]> a productoDTO */
    public List<ProductoDTO> convertirProductoDTOPorId(List<Producto> resultObj) {
        return resultObj.stream().map(lData -> {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setId(lData.getId());
            productoDTO.setNombre(lData.getNombre());
            productoDTO.setPrecio(lData.getPrecio());
            productoDTO.setEstado(lData.getEstado());
            productoDTO.setFechaRegistro((Date) lData.getFechaRegistro());
            return productoDTO;
        }).collect(Collectors.toList());
    }

    /** Metodos mapeo DTO para consultas */
    public List<ProductoDTO> convertirProductoDTOAll(List<Producto> resultObj) {
        return resultObj.stream().map(lData -> {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setId(lData.getId());
            productoDTO.setNombre(lData.getNombre().trim());
            productoDTO.setPrecio(lData.getPrecio());
            productoDTO.setEstado(lData.getEstado().trim());
            productoDTO.setFechaRegistro(lData.getFechaRegistro());
            return productoDTO;
        }).collect(Collectors.toList());
    }
}
