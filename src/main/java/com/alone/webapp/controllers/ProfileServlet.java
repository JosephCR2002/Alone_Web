package com.alone.webapp.controllers;

import com.alone.webapp.DAO.CategoriaDAO;
import com.alone.webapp.DAO.UsuarioDAO;
import com.alone.webapp.models.Categoria;
import com.alone.webapp.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
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
        try {
            this.updateProfile(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            ProductosServlet.sendInternalError(response, e);
        }
    }

    private void defaultAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        Usuario usuario = usuarioDAO.findById(id);
        request.setAttribute("u",usuario);

        getServletContext().getRequestDispatcher("/WEB-INF/user/profile.jsp").forward(request,response);
    }

    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = UsuariosServlet.getId(request);
        boolean methodSuccess = UsuariosServlet.addUsuario(request,usuarioDAO);
        String msg = "'\u00a1Error al actualizar los datos!'";
        if(methodSuccess){
            msg = "'\u00a1Datos Actualizados con \u00e9xito!'";
        }
        request.setAttribute("msg",msg);
        response.sendRedirect(request.getContextPath()+"/profile?id=");
    }
}
