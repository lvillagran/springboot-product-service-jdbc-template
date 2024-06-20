package com.producto.api.repository;

import com.producto.api.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ProductoRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public ProductoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Insertar producto
     */
    @Transactional
    public void registrar(Producto producto) {

        int lValorSecuencia = 0;
        lValorSecuencia = generaSecuencia();

        String lqueryInsert = " insert into  producto (id, nombre, precio, estado, fechaRegistro) values (?,?,?,?,?); ";

        jdbcTemplate.update(lqueryInsert, lValorSecuencia, producto.getNombre(), producto.getPrecio(), producto.getEstado(), producto.getFechaRegistro());
    }


    public int generaSecuencia() {
        String sqlSecuencia = "SELECT NEXT VALUE FOR ProductoSequence";
        return jdbcTemplate.queryForObject(sqlSecuencia, Integer.class);
    }

    /**
     * Consultar todos los productos
     */
    public List<Producto> listaTodosProductos() {
        String lqueryConsulta = "SELECT id, nombre, precio, estado, fechaRegistro FROM producto";

        return jdbcTemplate.query(lqueryConsulta, new RowMapper<Producto>() {
            @Override
            public Producto mapRow(ResultSet result, int rowNum) throws SQLException {
                Producto lData = new Producto();
                lData.setId(result.getLong("id"));
                lData.setNombre(result.getString("nombre").trim());
                lData.setPrecio(result.getDouble("precio"));
                lData.setEstado(result.getString("estado"));
                lData.setFechaRegistro(result.getDate("fechaRegistro"));
                return lData;
            }
        });
    }


    /**
     * Consultar producto por Id
     */
    public List<Producto> productosById(Long id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Producto.class));
    }


    /**
     * Actualizar producto por Id
     */
    public void actualizaPrecioById(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, fechaActualizacion = ? WHERE id = ?";
        jdbcTemplate.update(sql, producto.getNombre(), producto.getPrecio(), producto.getFechaActualizacion(), producto.getId());
    }


    /**
     * Eliminar producto por Id
     */
    @Transactional
    public void eliminarProductoById(Producto producto) {
        String sql = "DELETE FROM producto WHERE id = ?";
        jdbcTemplate.update(sql, producto.getId());
    }
}

