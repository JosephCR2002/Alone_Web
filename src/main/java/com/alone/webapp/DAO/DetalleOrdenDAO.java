package com.alone.webapp.DAO;

import com.alone.webapp.models.DetalleOrden;
import com.alone.webapp.models.Producto;
import com.alone.webapp.util.Conexion;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenDAO {

    public List<DetalleOrden> findAll() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<DetalleOrden> detalleOrdenes = new ArrayList<>();
        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT d.*, p.producto_precio, p.producto_descripcion from detalle_ordenes d inner join productos p on d.producto_id = p.producto_id")) {
            while (rs.next()) {
                DetalleOrden detalleOrden = getDetalleOrden(rs);
                detalleOrdenes.add(detalleOrden);
            }
        }
        return detalleOrdenes;
    }

    public DetalleOrden findById(int ordenId, int productoId) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        DetalleOrden detalleOrden = new DetalleOrden();
        try (PreparedStatement stmt = cn.prepareStatement("SELECT d.*, p.producto_precio, p.producto_descripcion from detalle_ordenes d inner join productos p on d.producto_id = p.producto_id where orden_id=? and d.producto_id=?")) {
            stmt.setInt(1, ordenId);
            stmt.setInt(2, productoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    detalleOrden = getDetalleOrden(rs);
                }
            }
        }
        return detalleOrden;
    }

    public List<DetalleOrden> findByOrderId(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<DetalleOrden> detalleOrdenes = new ArrayList<>();
        try (PreparedStatement stmt = cn.prepareStatement("SELECT d.*, p.producto_precio, p.producto_descripcion from detalle_ordenes d inner join productos p on d.producto_id = p.producto_id where orden_id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalleOrden detalleOrden = getDetalleOrden(rs);
                    detalleOrdenes.add(detalleOrden);
                }
            }
        }
        return detalleOrdenes;
    }

    public void add(DetalleOrden detalleOrden) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql = "insert into detalle_ordenes (orden_id, producto_id, detalle_orden_precio, detalle_orden_cantidad) values (?,?,?,?)";

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, detalleOrden.getId());
            pst.setInt(2, detalleOrden.getProducto().getId());
            pst.setBigDecimal(3, BigDecimal.valueOf(detalleOrden.getPrecio()));
            pst.setInt(4, detalleOrden.getCantidad());
            pst.executeUpdate();
        }
    }

    public void update(DetalleOrden detalleOrden, int producto_id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql = "update detalle_ordenes set producto_id=?,detalle_orden_precio=?,detalle_orden_cantidad=? where orden_id=? and producto_id=?";
        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, detalleOrden.getProducto().getId());
            pst.setBigDecimal(2, BigDecimal.valueOf(detalleOrden.getPrecio()));
            pst.setInt(3, detalleOrden.getCantidad());
            pst.setInt(4, detalleOrden.getId());
            pst.setInt(5, producto_id);
            pst.executeUpdate();
        }
    }

    public void delete(int ordenId, int productoId) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try (PreparedStatement stmt = cn.prepareStatement("delete from detalle_ordenes where orden_id=? and producto_id=?")) {
            stmt.setInt(1, ordenId);
            stmt.setInt(2, productoId);
            stmt.executeUpdate();
        }
    }

    public void deleteAll(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try (PreparedStatement stmt = cn.prepareStatement("delete from detalle_ordenes where orden_id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public BigDecimal findMonthlyWealth() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        BigDecimal ganancia = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT SUM(detalle_orden_precio) as ganancia FROM detalle_ordenes d INNER JOIN ordenes o ON d.orden_id = o.orden_id WHERE MONTH(o.orden_fecha_creacion) = MONTH(CURRENT_DATE) and year(o.orden_fecha_creacion) = year(CURRENT_DATE)")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ganancia = rs.getBigDecimal("ganancia");
                }
            }
        }
        return ganancia;
    }

    private DetalleOrden getDetalleOrden(ResultSet rs) throws SQLException {
        DetalleOrden d = new DetalleOrden();
        Producto p = new Producto();
        d.setId(rs.getInt("orden_id"));
        p.setId(rs.getInt("producto_id"));
        d.setPrecio(rs.getBigDecimal("detalle_orden_precio").doubleValue());
        d.setCantidad(rs.getInt("detalle_orden_cantidad"));
        p.setDescripcion(rs.getString("producto_descripcion"));
        p.setPrecio(rs.getBigDecimal("producto_precio"));
        d.setProducto(p);

        return d;
    }
}
