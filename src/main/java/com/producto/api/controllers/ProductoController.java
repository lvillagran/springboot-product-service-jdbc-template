package com.producto.api.controllers;

import com.producto.api.dto.ProductoDTO;
import com.producto.api.dto.ProductoRequestDTO;
import com.producto.api.dto.ProductoResponseDTO;
import com.producto.api.model.Producto;
import com.producto.api.service.ProductoService;
import com.producto.api.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimiento/producto-service")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    /**
     * Registra prodcucto
     */
    @PostMapping("/registrar")
    public ResponseEntity<ProductoResponseDTO> registraProducto(@Validated @RequestBody ProductoRequestDTO request) {
         ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();
         Producto producto = new Producto();

         try {
             producto.setNombre(request.getNombre().toString().trim());
             producto.setPrecio(request.getPrecio());
             producto.setEstado(Constantes.ACTIVO);
             producto.setFechaRegistro(new Date());
             productoService.registrar(producto);

             productoResponseDTO.setMensaje(Constantes.TRANSACCION_EXITOSA);
             return ResponseEntity.ok(productoResponseDTO);

         } catch (Exception e) {
             System.out.println("ERROR: " + e.getStackTrace());
             productoResponseDTO.setMensaje(Constantes.ERROR_REGISTRAR);
             return ResponseEntity.status(500).body(productoResponseDTO);
         }
     }


        /**
         * Consulta lista de productos
         */
         @PostMapping("/listadoProductos")
         public ResponseEntity<ProductoResponseDTO> productosAll() {
             ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();

             try {
                 List<ProductoDTO> listaProducto = productoService.listaTodosProductos();
                 productoResponseDTO.setProducto(listaProducto);
                 productoResponseDTO.setMensaje(Constantes.TRANSACCION_EXITOSA);
                 return ResponseEntity.ok(productoResponseDTO);

             } catch (Exception e) {
                 System.out.println("ERROR: " + e.getStackTrace());
                 productoResponseDTO.setMensaje(Constantes.ERROR_LISTAR);
                 return ResponseEntity.status(500).body(productoResponseDTO);
             }
         }

    /**
     * Actualizacion de producto por Id
     */
     @PutMapping("/update")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@Validated @RequestBody ProductoRequestDTO request) {

        ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();

        try {
            List<ProductoDTO> resultProducto = productoService.consultaProductosPorId(request.getId());

            if (resultProducto == null || resultProducto.isEmpty()) {
                productoResponseDTO.setMensaje(Constantes.REGISTRO_NO_ENCONTRADO);
                return ResponseEntity.ok(productoResponseDTO);
            } else {
                Producto producto = new Producto();
                producto.setId(resultProducto.get(0).getId());
                producto.setEstado(resultProducto.get(0).getEstado());
                producto.setFechaRegistro(resultProducto.get(0).getFechaRegistro());
                producto.setNombre(resultProducto.get(0).getNombre());

                /** Actulizo el precio */
                producto.setPrecio(request.getPrecio());
                producto.setFechaActualizacion(new Date());

                productoService.actualizarProducto(producto);
                productoResponseDTO.setMensaje(Constantes.TRANSACCION_EXITOSA);
                return ResponseEntity.ok(productoResponseDTO);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getStackTrace());
            productoResponseDTO.setMensaje(Constantes.ERROR_ACTUALIZAR + " - "+ e.getStackTrace());
            return ResponseEntity.ok(productoResponseDTO);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<ProductoResponseDTO> deleteProducto(@Validated @RequestBody ProductoRequestDTO request) {

        ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO();

        try {
            List<ProductoDTO> resultProducto = productoService.consultaProductosPorId(request.getId());

            if (resultProducto == null || resultProducto.isEmpty()) {
                productoResponseDTO.setMensaje(Constantes.REGISTRO_NO_ENCONTRADO);
                return ResponseEntity.ok(productoResponseDTO);
            } else {
                Producto producto = new Producto();
                producto.setId(resultProducto.get(0).getId());
                producto.setFechaRegistro(resultProducto.get(0).getFechaRegistro());
                producto.setNombre(resultProducto.get(0).getNombre());
                producto.setPrecio(resultProducto.get(0).getPrecio());
                producto.setEstado(Constantes.ELIMINADO);

                productoService.deleteProducto(producto);
                productoResponseDTO.setMensaje(Constantes.TRANSACCION_EXITOSA);
                return ResponseEntity.ok(productoResponseDTO);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getStackTrace());
            productoResponseDTO.setMensaje(Constantes.ERROR_ACTUALIZAR);
            return ResponseEntity.status(500).body(productoResponseDTO);
        }
    }
}
