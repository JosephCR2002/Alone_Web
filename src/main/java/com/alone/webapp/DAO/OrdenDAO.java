package com.alone.webapp.DAO;

import com.alone.webapp.models.*;
import com.alone.webapp.util.Conexion;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenDAO implements DAO<Orden> {

    @Override
    public List<Orden> findAll() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Orden> ordenes = new ArrayList<>();
        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT o.*, u.usuario_nombre ,d.producto_id," +
                     "p.producto_descripcion,p.producto_precio," +
                     "d.detalle_orden_cantidad,d.detalle_orden_precio" +
                     " from ordenes o inner join usuarios u on " +
                     "o.usuario_id = u.usuario_id inner join" +
                     " detalle_ordenes d on o.orden_id = d.orden_id" +
                     " inner join productos p on d.producto_id = p.producto_id")) {
            while (rs.next()) {
                Orden orden = getOrden(rs);
                ordenes.add(orden);
            }
        }
        return ordenes;
    }

    public List<Orden> findDaily() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Orden> ordenes = new ArrayList<>();
        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT o.*, u.usuario_nombre ,d.producto_id," +
                     "p.producto_descripcion,p.producto_precio," +
                     "d.detalle_orden_cantidad,d.detalle_orden_precio" +
                     " from ordenes o inner join usuarios u on " +
                     "o.usuario_id = u.usuario_id inner join" +
                     " detalle_ordenes d on o.orden_id = d.orden_id" +
                     " inner join productos p on d.producto_id = p.producto_id where day(o.orden_fecha_creacion) = day(current_date)")) {
            while (rs.next()) {
                Orden orden = getOrden(rs);
                ordenes.add(orden);
            }
        }
        return ordenes;
    }

    @Override
    public Orden findById(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Orden orden = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT o.*, u.usuario_nombre ,d.producto_id," +
                "p.producto_descripcion,p.producto_precio," +
                "d.detalle_orden_cantidad,d.detalle_orden_precio" +
                " from ordenes o inner join usuarios u on " +
                "o.usuario_id = u.usuario_id inner join" +
                " detalle_ordenes d on o.orden_id = d.orden_id" +
                " inner join productos p on d.producto_id = p.producto_id where o.orden_id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orden = getOrden(rs);
                }
            }
        }
        return orden;
    }

    public Integer findId(int usuarioId, LocalDateTime creationDate) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Integer ordenId = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT orden_id from ordenes where usuario_id=? and orden_fecha_creacion=?")) {
            stmt.setInt(1, usuarioId);
            stmt.setTimestamp(2, Timestamp.valueOf(creationDate));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ordenId = rs.getInt("orden_id");
                }
            }
        }
        return ordenId;
    }

    @Override
    public void add(Orden orden) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql;
        if (orden.getId() != null && orden.getId() > 0) {
            sql = "update ordenes set usuario_id=? where orden_id=?";
        } else {
            sql = "insert into ordenes (usuario_id, orden_fecha_creacion) values (?,?)";
        }

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, orden.getUsuario().getId());
            if (orden.getId() != null && orden.getId() > 0) {
                pst.setInt(2, orden.getId());
            } else {
                pst.setTimestamp(2, Timestamp.valueOf(orden.getFechaCreacion()));
            }
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try (PreparedStatement stmt = cn.prepareStatement("delete from ordenes where orden_id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Integer findMonthlyOrders() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Integer ordenes = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT COUNT(*) as ordenes FROM ordenes o WHERE MONTH(o.orden_fecha_creacion) = MONTH(CURRENT_DATE) and year(o.orden_fecha_creacion) = year(CURRENT_DATE)")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ordenes = rs.getInt("ordenes");
                }
            }
        }
        return ordenes;
    }

    public Integer findYearlyCustomers() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Integer clientes = null;
        try (PreparedStatement stmt = cn.prepareStatement("select count(distinct usuario_id) as clientes from ordenes WHERE year(orden_fecha_creacion) = year(CURRENT_DATE);")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    clientes = rs.getInt("clientes");
                }
            }
        }
        return clientes;
    }

    private Orden getOrden(ResultSet rs) throws SQLException {
        Orden o = new Orden();
        Usuario u = new Usuario();
        Producto p = new Producto();
        DetalleOrden d = new DetalleOrden();
        o.setId(rs.getInt("orden_id"));
        u.setId(rs.getInt("usuario_id"));
        o.setFechaCreacion(rs.getTimestamp("orden_fecha_creacion").toLocalDateTime());
        u.setNombre(rs.getString("usuario_nombre"));
        p.setId(rs.getInt("producto_id"));
        p.setDescripcion(rs.getString("producto_descripcion"));
        p.setPrecio(rs.getBigDecimal("producto_precio"));
        d.setCantidad(rs.getInt("detalle_orden_cantidad"));
        d.setPrecio(rs.getBigDecimal("detalle_orden_precio").doubleValue());

        o.setUsuario(u);
        o.setProducto(p);
        o.setDetalleOrden(d);
        return o;
    }
}
