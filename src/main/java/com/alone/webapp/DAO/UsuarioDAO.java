package com.alone.webapp.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.alone.webapp.models.Usuario;
import com.alone.webapp.util.Conexion;

public class UsuarioDAO implements DAO<Usuario> {

    @Override
    public List<Usuario> findAll() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from usuarios")) {
            while (rs.next()) {
                Usuario usuario = getUsuario(rs);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    @Override
    public Usuario findById(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Usuario usuario = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT * from usuarios where usuario_id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public void add(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "update usuarios set usuario_nombre=?, usuario_telefono=?, usuario_email=?, usuario_nivel=?, usuario_password=? where usuario_id=?";
        } else {
            sql = "insert into usuarios (usuario_nombre, usuario_telefono, usuario_email, usuario_nivel, usuario_password, usuario_fecha_creacion) values (?,?,?,?,?,?)";
        }

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getTelefono());
            pst.setString(3, usuario.getEmail());
            pst.setInt(4, usuario.getNivel());
            pst.setString(5, usuario.getPassword());
            if (usuario.getId() != null && usuario.getId() > 0) {
                pst.setInt(6, usuario.getId());
            } else {
                pst.setTimestamp(6, Timestamp.valueOf(usuario.getFechaCreacion()));
            }
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try(PreparedStatement stmt = cn.prepareStatement("delete from usuarios where usuario_id=?")){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }

    public Usuario login(String email, String password) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Usuario usuario = null;
        PreparedStatement pst;
        ResultSet rs = null;

        try {
            String vsql = "select * from usuarios where usuario_email=? and usuario_password=?";
            pst = cn.prepareStatement(vsql);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                usuario = getUsuario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("usuario_id"));
        u.setNombre(rs.getString("usuario_nombre"));
        u.setTelefono(rs.getString("usuario_telefono"));
        u.setEmail(rs.getString("usuario_email"));
        u.setPassword(rs.getString("usuario_password"));
        u.setNivel(rs.getInt("usuario_nivel"));
        u.setFechaCreacion(rs.getTimestamp("usuario_fecha_creacion").toLocalDateTime());
        return u;
    }
}
