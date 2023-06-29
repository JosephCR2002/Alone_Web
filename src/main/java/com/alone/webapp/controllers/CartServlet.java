package com.alone.webapp.controllers;

import com.alone.webapp.DAO.DetalleOrdenDAO;
import com.alone.webapp.DAO.OrdenDAO;
import com.alone.webapp.DAO.ProductoDAO;
import com.alone.webapp.DAO.UsuarioDAO;
import com.alone.webapp.models.Producto;
import com.alone.webapp.models.Usuario;
import com.alone.webapp.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

    private ProductoDAO productoDAO = new ProductoDAO();
    private OrdenDAO ordenDAO = new OrdenDAO();
    private DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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
                String page = request.getParameter("page");

                switch (page) {
                    case "cart":
                        try {
                            this.redirectToCart(request, response);
                        } catch (Exception e) {
                            ProductosServlet.sendInternalError(response, e);
                        }
                        break;
                    case "payment_success":
                        try {
                            this.redirectToSuccessPage(request, response);
                        } catch (Exception e) {
                            ProductosServlet.sendInternalError(response, e);
                        }
                        break;
                    default:
                        UsuariosServlet.sendError(response);
                        break;
                }
                break;
            default:
                UsuariosServlet.sendError(response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        if (action == null) {
            UsuariosServlet.sendError(response);
            return;
        }

        switch (action) {
            case "payment":
                try {
                    out.print(this.confirmPayment(request, ordenDAO, detalleOrdenDAO, usuarioDAO, productoDAO));
                } catch (SQLException | ClassNotFoundException e) {
                    ProductosServlet.sendInternalError(response, e);
                }
                break;
            default:
                UsuariosServlet.sendError(response);
                break;
        }
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

    private void redirectToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/user/cart/cart.jsp").forward(request, response);
    }

    private void redirectToSuccessPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/user/cart/payment_success.jsp").forward(request, response);
    }

    private String confirmPayment(HttpServletRequest request, OrdenDAO ordenDAO, DetalleOrdenDAO detalleOrdenDAO, UsuarioDAO usuarioDAO, ProductoDAO productoDAO) throws SQLException, ClassNotFoundException, IOException {
        Usuario usuario;

        try {
            usuario = Util.getSessionUser(request);
        } catch (Exception e) {
            return "false";
        }

        // Obtiene el tipo de objeto del arrayList de productos
        final Type productsListType = new TypeToken<ArrayList<Producto>>() {
        }.getType();

        // Convierte el JSON en un objeto Java utilizando Gson
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String jsonBody = Util.getRequestBody(request);
        JsonObject jsonObject = gson.fromJson(jsonBody, JsonObject.class);

        ArrayList<Producto> productos = new ArrayList<>();

        // Recorrer las propiedades del objeto JSON y convertirlas en objetos Producto
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonObject productJson = entry.getValue().getAsJsonObject();

            Producto producto = gson.fromJson(productJson, Producto.class);
            productos.add(producto);
        }

        // Se crea la orden de compra y se obtiene el id
        int ordenId = OrdenServlet.setOrden(usuarioDAO, ordenDAO, usuario.getId());

        // Procesar el pago para cada producto
        for (Producto producto : productos) {
            if (!OrdenServlet.paymentWentGood(
                    productoDAO,
                    detalleOrdenDAO,
                    producto.getId(),
                    ordenId,
                    producto.getCantidad())) {
                return "false";
            }
        }

        return "true";
    }
}
