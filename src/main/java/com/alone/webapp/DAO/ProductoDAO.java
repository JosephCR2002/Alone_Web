package com.alone.webapp.DAO;

import com.alone.webapp.models.Categoria;
import com.alone.webapp.models.Producto;
import com.alone.webapp.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements DAO<Producto> {

    @Override
    public List<Producto> findAll() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = cn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT p.*, c.categoria_descripcion from productos p inner join categorias c on p.categoria_id = c.categoria_id order by p.producto_fecha_creacion desc")) {
            while (rs.next()) {
                Producto producto = getProducto(rs);
                productos.add(producto);
            }
        }
        return productos;
    }

    public List<Producto> findByGender(int genero) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = cn.prepareStatement("SELECT p.*, c.categoria_descripcion from productos p inner join categorias c on p.categoria_id = c.categoria_id where c.categoria_genero=? order by p.producto_fecha_creacion desc limit 6")) {
            stmt.setInt(1, genero);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = getProducto(rs);
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

    public List<Producto> findBestSellers() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = cn.prepareStatement("SELECT p.*, c.categoria_descripcion " +
                "FROM productos p INNER JOIN categorias c ON p.categoria_id = c.categoria_id " +
                "INNER JOIN detalle_ordenes d ON p.producto_id = d.producto_id GROUP BY " +
                "d.producto_id ORDER BY SUM(d.detalle_orden_cantidad) desc limit 3")) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = getProducto(rs);
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

    public List<Producto> findByTag(int categoria) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement stmt = cn.prepareStatement("SELECT p.*, c.categoria_descripcion from productos p inner join categorias c on p.categoria_id = c.categoria_id where c.categoria_id=? order by p.producto_fecha_creacion desc")) {
            stmt.setInt(1, categoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = getProducto(rs);
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

    @Override
    public Producto findById(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Producto producto = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT p.*, c.categoria_descripcion from productos p inner join categorias c on p.categoria_id = c.categoria_id where producto_id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void add(Producto producto) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "update productos set categoria_id=?,producto_precio=?,producto_descripcion=?,producto_imagen=?,producto_estado=?,producto_inventario=?  where producto_id=?";
        } else {
            sql = "insert into productos (categoria_id, producto_precio, producto_descripcion, producto_imagen, producto_estado, producto_inventario,producto_fecha_creacion) values (?,?,?,?,?,?,?)";
        }

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, producto.getCategoria().getId());
            pst.setBigDecimal(2, producto.getPrecio());
            pst.setString(3, producto.getDescripcion());
            pst.setString(4, producto.getImagen());
            pst.setInt(5, producto.getEstado());
            pst.setInt(6, producto.getInventario());
            if (producto.getId() != null && producto.getId() > 0) {
                pst.setInt(7, producto.getId());
            } else {
                pst.setTimestamp(7, Timestamp.valueOf(producto.getFechaCreacion()));
            }
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try (PreparedStatement stmt = cn.prepareStatement("delete from productos where producto_id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        Categoria c = new Categoria();
        p.setId(rs.getInt("producto_id"));
        c.setId(rs.getInt("categoria_id"));
        p.setPrecio(rs.getBigDecimal("producto_precio"));
        p.setDescripcion(rs.getString("producto_descripcion"));
        p.setImagen(rs.getString("producto_imagen"));
        p.setEstado(rs.getInt("producto_estado"));
        p.setInventario(rs.getInt("producto_inventario"));
        p.setFechaCreacion(rs.getTimestamp("producto_fecha_creacion").toLocalDateTime());

        c.setDescripcion(rs.getString("categoria_descripcion"));
        p.setCategoria(c);
        return p;
    }
}
