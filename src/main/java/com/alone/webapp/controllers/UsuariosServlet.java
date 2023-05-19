package com.alone.webapp.controllers;

import com.alone.webapp.DAO.UsuarioDAO;
import com.alone.webapp.models.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "UsuariosServlet", value = "/usuarios")
public class UsuariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String view = request.getParameter("view");
        if (view != null) {
            switch (view) {
                case "list":
                    try {
                        this.viewList(request, response, usuarioDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "add-form":
                    this.viewAddForm(request, response);
                    break;
                case "update-form":
                    try {
                        this.viewUpdateForm(request, response, usuarioDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "delete":
                    try {
                        this.deleteUser(request, response, usuarioDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                default:
                    sendError(response);
                    break;
            }
        } else {
            sendError(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean methodSuccess;
        String msg = "";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    msg = "'\u00A1Error al a\u00f1adir el usuario!'";
                    methodSuccess = addUsuario(request, usuarioDAO);
                    if (methodSuccess) {
                        msg = "'\u00a1Usuario a\u00f1adido con \u00e9xito!'";
                    }
                    request.setAttribute("msg", msg);
                    viewAddForm(request, response);
                    break;
                case "update":
                    try {
                        msg = "'\u00a1Error al actualizar el usuario!'";
                        methodSuccess = addUsuario(request, usuarioDAO);
                        if (methodSuccess) {
                            msg = "'\u00a1Usuario actualizado con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewList(request, response, usuarioDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                default:
                    sendError(response);
                    break;
            }
        } else {
            sendError(response);
        }
    }

    private void viewList(HttpServletRequest request, HttpServletResponse response, UsuarioDAO usuarioDAO) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Usuario> usuarios = usuarioDAO.findAll();
        request.setAttribute("usuarios", usuarios);
        getServletContext().getRequestDispatcher("/WEB-INF/dashboard/usuarios/listado.jsp").forward(request, response);
    }

    private void viewAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addForm = "/WEB-INF/dashboard/usuarios/anadir-formulario.jsp";
        request.getRequestDispatcher(addForm).forward(request, response);
    }

    private void viewUpdateForm(HttpServletRequest request, HttpServletResponse response, UsuarioDAO usuarioDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = getId(request);
        Usuario usuario = usuarioDAO.findById(id);
        request.setAttribute("usuario", usuario);
        String updateForm = "/WEB-INF/dashboard/usuarios/editar-formulario.jsp";
        request.getRequestDispatcher(updateForm).forward(request, response);
    }

    public static void sendError(HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos, el recurso solicitado no está disponible");
    }

    public static boolean addUsuario(HttpServletRequest request, UsuarioDAO usuarioDAO) {
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int nivel = Integer.parseInt(request.getParameter("nivel"));
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Integer id = getId(request);

        //Se crean los modelos
        Usuario usuario = new Usuario(id, nombre, telefono, email, password, nivel, fechaCreacion);
        //Se crea la respuesta
        boolean msg = false;
        try {
            usuarioDAO.add(usuario);
            msg = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response, UsuarioDAO usuarioDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = getId(request);
        usuarioDAO.delete(id);
        this.viewList(request, response, usuarioDAO);
    }

    public static Integer getId(HttpServletRequest request) {
        String idStr;
        try {
            idStr = request.getParameter("id");
        } catch (NullPointerException e) {
            idStr = "";
        }
        Integer id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            id = 0;
        }
        return id;
    }
}
