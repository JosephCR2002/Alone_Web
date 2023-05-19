package com.alone.webapp.controllers;

import com.alone.webapp.DAO.CategoriaDAO;
import com.alone.webapp.models.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "CategoriasServlet", value = "/categorias")
public class CategoriasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        String view = request.getParameter("view");
        if (view != null) {
            switch (view) {
                case "list":
                    try {
                        this.viewList(request, response, categoriaDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "add-form":
                    this.viewAddForm(request, response);
                    break;
                case "update-form":
                    try {
                        this.viewUpdateForm(request, response, categoriaDAO);
                    } catch (SQLException | ClassNotFoundException e) {
                        ProductosServlet.sendInternalError(response, e);
                    }
                    break;
                case "delete":
                    try {
                        this.deleteCategoria(request, response, categoriaDAO);
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
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        boolean methodSuccess;
        String msg = "";
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    msg = "'\u00A1Error al a\u00f1adir la categor\u00eda!'";
                    methodSuccess = addCategoria(request, categoriaDAO);
                    if (methodSuccess) {
                        msg = "'\u00a1Categor\u00eda a\u00f1adida con \u00e9xito!'";
                    }
                    request.setAttribute("msg", msg);
                    viewAddForm(request, response);
                    break;
                case "update":
                    try {
                        msg = "'\u00a1Error al actualizar la categor\u00eda!'";
                        methodSuccess = addCategoria(request, categoriaDAO);
                        if (methodSuccess) {
                            msg = "'\u00a1Categor\u00eda actualizada con \u00e9xito!'";
                        }
                        request.setAttribute("msg", msg);
                        viewList(request, response, categoriaDAO);
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

    private void viewList(HttpServletRequest request, HttpServletResponse response, CategoriaDAO categoriaDAO) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Categoria> categorias = categoriaDAO.findAll();
        request.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/WEB-INF/dashboard/categorias/listado.jsp").forward(request, response);
    }

    private void viewAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addForm = "/WEB-INF/dashboard/categorias/anadir-formulario.jsp";
        request.getRequestDispatcher(addForm).forward(request, response);
    }

    private void viewUpdateForm(HttpServletRequest request, HttpServletResponse response, CategoriaDAO categoriaDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        Categoria categoria = categoriaDAO.findById(id);
        request.setAttribute("categoria", categoria);
        String updateForm = "/WEB-INF/dashboard/categorias/editar-formulario.jsp";
        request.getRequestDispatcher(updateForm).forward(request, response);
    }

    private static boolean addCategoria(HttpServletRequest request, CategoriaDAO categoriaDAO) {
        String descripcion = request.getParameter("descripcion");
        int genero = Integer.parseInt(request.getParameter("genero"));
        String img = request.getParameter("img");
        Integer id = UsuariosServlet.getId(request);

        //Se crean los modelos
        Categoria categoria = new Categoria(id,descripcion,genero,img);
        //Se crea la respuesta
        boolean msg = false;
        try {
            categoriaDAO.add(categoria);
            msg = true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void deleteCategoria(HttpServletRequest request, HttpServletResponse response, CategoriaDAO categoriaDAO) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer id = UsuariosServlet.getId(request);
        categoriaDAO.delete(id);
        this.viewList(request, response, categoriaDAO);
    }
}
