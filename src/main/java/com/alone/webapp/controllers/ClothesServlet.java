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

@WebServlet(name = "ClothesServlet", value = "/clothes")
public class ClothesServlet extends HttpServlet {
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        if (view != null) {
            switch (view) {
                case "tag":
                    try {
                        this.viewByTag(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "gender":
                    try {
                        this.viewByGender(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "id":
                    try {
                        this.viewById(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "bestSellers":
                    try {
                        this.viewByBestSellers(request, response);
                    } catch (SQLException | ClassNotFoundException | ServletException | IOException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                default:
                    try {
                        this.defaultAction(request, response);
                    } catch (SQLException | ClassNotFoundException | ServletException | IOException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
            }
        } else {
            try {
                this.defaultAction(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                ProductosServlet.sendInternalError(response, e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Producto> productos = productoDAO.findAll();
        request.setAttribute("productos", productos);

        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);

        request.setAttribute("title", "Todos Los Productos");

        getServletContext().getRequestDispatcher("/WEB-INF/user/products/products.jsp").forward(request, response);
    }

    private void viewByGender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        List<Producto> productos = productoDAO.findByGender(id);
        request.setAttribute("productos", productos);

        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);

        String title = "";

        switch (id) {
            case 0:
                title = "Moda Hombres";
                break;
            case 1:
                title = "Moda Mujeres";
                break;
        }

        request.setAttribute("title", title);

        getServletContext().getRequestDispatcher("/WEB-INF/user/products/products.jsp").forward(request, response);
    }

    private void viewByTag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        List<Producto> productos = productoDAO.findByTag(id);
        request.setAttribute("productos", productos);

        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);

        Categoria categoria = categoriaDAO.findById(id);

        String title = categoria.getDescripcion();

        request.setAttribute("title", title);

        getServletContext().getRequestDispatcher("/WEB-INF/user/products/products.jsp").forward(request, response);
    }

    private void viewById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        Producto producto = productoDAO.findById(id);
        request.setAttribute("p", producto);

        getServletContext().getRequestDispatcher("/WEB-INF/user/products/product.jsp").forward(request, response);
    }

    private void viewByBestSellers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Producto> productos = productoDAO.findBestSellers();
        req.setAttribute("productos", productos);

        List<Categoria> categorias = categoriaDAO.findAll();
        req.setAttribute("categorias", categorias);

        req.setAttribute("title", "Nuestros Productos M&aacute;s Comprados");

        getServletContext().getRequestDispatcher("/WEB-INF/user/products/products.jsp").forward(req, resp);
    }
}
