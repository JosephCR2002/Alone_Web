package com.alone.webapp.controllers;

import com.alone.webapp.DAO.CategoriaDAO;
import com.alone.webapp.DAO.ProductoDAO;
import com.alone.webapp.models.Categoria;
import com.alone.webapp.models.Producto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.defaultAction(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            ProductosServlet.sendInternalError(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias",categorias);

        List<Producto> productosHombre = productoDAO.findByGender(0);
        request.setAttribute("productosHombre",productosHombre);

        List<Producto> productosMujer = productoDAO.findByGender(1);
        request.setAttribute("productosMujer",productosMujer);

        getServletContext().getRequestDispatcher("/WEB-INF/user/index.jsp").forward(request,response);
    }
}
