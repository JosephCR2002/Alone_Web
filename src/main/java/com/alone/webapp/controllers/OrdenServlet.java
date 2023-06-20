package com.alone.webapp.controllers;

import com.alone.webapp.DAO.*;
import com.alone.webapp.models.Orden;
import com.alone.webapp.models.DetalleOrden;
import com.alone.webapp.models.Producto;
import com.alone.webapp.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "OrdensServlet", value = "/ordenes")
public class OrdenServlet extends HttpServlet {
    private OrdenDAO ordenDAO = new OrdenDAO();
    private DetalleOrdenDAO detalleOrdenDAO = new DetalleOrdenDAO();
    private ProductoDAO productoDAO = new ProductoDAO();
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        if (view != null) {
            switch (view) {
                case "list":
                    try {
                        this.viewList(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "add-form":
                    try {
                        this.viewAddForm(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "update-form":
                    try {
                        this.viewUpdateForm(request, response, ordenDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "delete":
                    try {
                        this.deleteOrden(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
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
        OrdenDAO ordenDAO = new OrdenDAO();
        boolean methodSuccess;
        String msg = "";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    try {
                        msg = "'\u00A1Error al a\u00f1adir la orde!'";
                        methodSuccess = addOrden(request);
                        if (methodSuccess) {
                            msg = "'\u00a1Orden a\u00f1adida con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewAddForm(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "update":
                    try {
                        msg = "'\u00a1Error al actualizar la orden!'";
                        methodSuccess = updateOrden(request);
                        if (methodSuccess) {
                            msg = "'\u00a1Orden actualizada con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewList(request, response);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
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

    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Orden> ordenes = ordenDAO.findAll();
        request.setAttribute("ordenes", ordenes);
        getServletContext().getRequestDispatcher("/WEB-INF/dashboard/ordenes/listado.jsp").forward(request, response);
    }

    private void viewAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Usuario> usuarios = usuarioDAO.findAll();
        request.setAttribute("usuarios",usuarios);
        List<Producto> productos = productoDAO.findAll();
        request.setAttribute("productos",productos);

        //Hallar la id minima de usuario para dejarlo como seleccionado por defecto
        ArrayList<Integer> usuariosId = new ArrayList<>();
        usuarios.forEach((usuario -> usuariosId.add(usuario.getId())));
        Integer minUsuarioId = Collections.min(usuariosId);
        request.setAttribute("minUsuarioId", minUsuarioId);

        //Hallar la id minima de producto para dejarlo como seleccionado por defecto
        ArrayList<Integer> productoId = new ArrayList<>();
        productos.forEach((producto -> productoId.add(producto.getId())));
        Integer minProductoId = Collections.min(productoId);
        request.setAttribute("minProductoId", minProductoId);

        String addForm = "/WEB-INF/dashboard/ordenes/anadir-formulario.jsp";
        request.getRequestDispatcher(addForm).forward(request, response);
    }

    private void viewUpdateForm(HttpServletRequest request, HttpServletResponse response, OrdenDAO ordenDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        Orden orden = ordenDAO.findById(id);
        request.setAttribute("orden", orden);

        List<Usuario> usuarios = usuarioDAO.findAll();
        request.setAttribute("usuarios",usuarios);
        List<Producto> productos = productoDAO.findAll();
        request.setAttribute("productos",productos);

        String updateForm = "/WEB-INF/dashboard/ordenes/editar-formulario.jsp";
        request.getRequestDispatcher(updateForm).forward(request, response);
    }

    private boolean addOrden(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        LocalDateTime fechaCreacion = LocalDateTime.now();

        //Se encuentra el usuario y el producto de la base de datos
        Producto producto = productoDAO.findById(productoId);
        Usuario usuario = usuarioDAO.findById(usuarioId);

        //Se calcula el precio
        double precio = (producto.getPrecio().doubleValue()) * cantidad;

        //Se crean los modelos
        DetalleOrden detalleOrden = new DetalleOrden();
        Orden orden = new Orden(usuario,producto,detalleOrden,fechaCreacion);

        //Se crea la respuesta
        boolean msg = false;
        try {
            //Se crea la orden
            ordenDAO.add(orden);

            //Se obtiene la id de la orden
            Integer ordenId = ordenDAO.findId(usuarioId,fechaCreacion);

            //Se le pasan los datos al modelo
            detalleOrden.setId(ordenId);
            detalleOrden.setProducto(producto);
            detalleOrden.setPrecio(precio);
            detalleOrden.setCantidad(cantidad);

            //Se añade el detalle
            detalleOrdenDAO.add(detalleOrden);
            msg = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    private boolean updateOrden(HttpServletRequest request) throws SQLException, ClassNotFoundException{
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        int productoId = Integer.parseInt(request.getParameter("productoId"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int oldId = Integer.parseInt(request.getParameter("oldId"));
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Integer id = UsuariosServlet.getId(request);

        //Se encuentra el usuario y el producto de la base de datos
        Producto producto = productoDAO.findById(productoId);
        Usuario usuario = usuarioDAO.findById(usuarioId);

        //Se calcula el precio
        double precio = (producto.getPrecio().doubleValue()) * cantidad;

        //Se crean los modelos
        DetalleOrden detalleOrden = new DetalleOrden(id,producto,precio,cantidad);
        Orden orden = new Orden(id,usuario,producto,detalleOrden,fechaCreacion);

        //Se crea la respuesta
        boolean msg = false;
        try {
            //Se actualiza la orden
            ordenDAO.add(orden);

            //Se actualiza el detalle
            detalleOrdenDAO.update(detalleOrden,oldId);
            msg = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void deleteOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        ordenDAO.delete(id);

        this.viewList(request, response);
    }
}
