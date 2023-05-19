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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "ProductosServlet", value = "/productos")
public class ProductosServlet extends HttpServlet {
    private ProductoDAO productoDAO = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        if (view != null) {
            switch (view) {
                case "list":
                    try {
                        this.viewList(request, response, productoDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                case "add-form":
                    try {
                        this.viewAddForm(request, response, categoriaDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                case "update-form":
                    try {
                        this.viewUpdateForm(request, response, productoDAO, categoriaDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                case "delete":
                    try {
                        this.deleteProducto(request, response, productoDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                default:
                    UsuariosServlet.sendError(response);
                    break;
            }
        } else {
            UsuariosServlet.sendError(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean methodSuccess;
        String msg;
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    try {
                        msg = "'\u00A1Error al a\u00f1adir el producto!'";
                        methodSuccess = addProducto(request, productoDAO, categoriaDAO);
                        if (methodSuccess) {
                            msg = "'\u00a1Producto a\u00f1adido con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewAddForm(request, response, categoriaDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                case "update":
                    try {
                        msg = "'\u00a1Error al actualizar el producto!'";
                        methodSuccess = addProducto(request, productoDAO, categoriaDAO);
                        if (methodSuccess) {
                            msg = "'\u00a1Producto actualizado con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewList(request, response, productoDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        sendInternalError(response, e);
                    }
                    break;
                default:
                    UsuariosServlet.sendError(response);
                    break;
            }
        } else {
            UsuariosServlet.sendError(response);
        }
    }

    public static void sendInternalError(HttpServletResponse response, Exception e) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
    }

    private void viewList(HttpServletRequest request, HttpServletResponse response, ProductoDAO productoDAO) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Producto> productos = productoDAO.findAll();
        request.setAttribute("productos", productos);
        getServletContext().getRequestDispatcher("/WEB-INF/dashboard/productos/listado.jsp").forward(request, response);
    }

    private void viewAddForm(HttpServletRequest request, HttpServletResponse response, CategoriaDAO categoriaDAO) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);

        //Hallar la id minima para dejarlo como seleccionado por defecto
        ArrayList<Integer> categoriasId = new ArrayList<>();
        categorias.forEach((categoria -> categoriasId.add(categoria.getId())));
        Integer minId = Collections.min(categoriasId);
        request.setAttribute("minId", minId);

        String addForm = "/WEB-INF/dashboard/productos/anadir-formulario.jsp";
        request.getRequestDispatcher(addForm).forward(request, response);
    }

    private void viewUpdateForm(HttpServletRequest request, HttpServletResponse response, ProductoDAO productoDAO, CategoriaDAO categoriaDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        Producto producto = productoDAO.findById(id);
        request.setAttribute("producto", producto);
        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);
        String updateForm = "/WEB-INF/dashboard/productos/editar-formulario.jsp";
        request.getRequestDispatcher(updateForm).forward(request, response);
    }

    private static boolean addProducto(HttpServletRequest request, ProductoDAO productoDAO, CategoriaDAO categoriaDAO) throws SQLException, ClassNotFoundException {
        BigDecimal precio = BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio")));
        String descripcion = request.getParameter("descripcion");
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
        String img = request.getParameter("img");
        int estado = Integer.parseInt(request.getParameter("estado"));
        int inventario = Integer.parseInt(request.getParameter("inventario"));
        Categoria categoria = categoriaDAO.findById(categoriaId);
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Integer id = UsuariosServlet.getId(request);

        //Se crean los modelos
        Producto producto = new Producto(id, precio, descripcion, categoria, img, estado, inventario, fechaCreacion);
        //Se crea la respuesta
        boolean msg = false;
        try {
            productoDAO.add(producto);
            msg = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void deleteProducto(HttpServletRequest request, HttpServletResponse response, ProductoDAO productoDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        productoDAO.delete(id);
        this.viewList(request, response, productoDAO);
    }
}
