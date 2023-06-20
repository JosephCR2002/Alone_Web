package com.alone.webapp.controllers;

import com.alone.webapp.DAO.ProductoDAO;
import com.alone.webapp.models.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");

        if (action == null) {
            UsuariosServlet.sendError(response);
            return;
        }

        switch (action) {
            case "find":
                try {
                    out.print(this.sendProductJson(request));
                } catch (SQLException | ClassNotFoundException e) {
                    ProductosServlet.sendInternalError(response, e);
                }
                break;
            case "view":
                try {
                    this.redirectToJsp(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    ProductosServlet.sendInternalError(response, e);
                }
                break;
            default:
                UsuariosServlet.sendError(response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String sendProductJson(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        Producto producto = productoDAO.findById(id);
        producto.setCantidad(1);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        return gson.toJson(producto);
    }

    private void redirectToJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        getServletContext().getRequestDispatcher("/WEB-INF/user/cart/cart.jsp").forward(request, response);
    }
}
