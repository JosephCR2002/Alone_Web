package com.alone.webapp.DAO;

import com.alone.webapp.models.Categoria;
import com.alone.webapp.models.Categoria;
import com.alone.webapp.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements DAO<Categoria> {

    @Override
    public List<Categoria> findAll() throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from categorias")) {
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    @Override
    public Categoria findById(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        Categoria categoria = null;
        try (PreparedStatement stmt = cn.prepareStatement("SELECT * from categorias where categoria_id=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void add(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        String sql;
        if (categoria.getId() != null && categoria.getId() > 0) {
            sql = "update categorias set categoria_descripcion=?, categoria_genero=?,categoria_img=?  where categoria_id=?";
        } else {
            sql = "insert into categorias (categoria_descripcion, categoria_genero,categoria_img) values (?,?,?)";
        }

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, categoria.getDescripcion());
            pst.setInt(2, categoria.getGenero());
            pst.setString(3, categoria.getImg());
            if (categoria.getId() != null && categoria.getId() > 0) {
                pst.setInt(4, categoria.getId());
            }
            pst.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection cn = Conexion.getConexion();
        try(PreparedStatement stmt = cn.prepareStatement("delete from categorias where categoria_id=?")){
            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }

    public static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getInt("categoria_id"));
        c.setDescripcion(rs.getString("categoria_descripcion"));
        c.setGenero(rs.getInt("categoria_genero"));
        c.setImg(rs.getString("categoria_img"));
        return c;
    }
}
