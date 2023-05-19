package com.alone.webapp.controllers;

import com.alone.webapp.models.Usuario;
import com.alone.webapp.DAO.UsuarioDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se recuperan los valores del formulario
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Se crean los modelos
        Usuario usuario;
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {//Se busca el usuario en la base de datos
            usuario = usuarioDAO.login(email, password);

            //Si el usuario existe
            if (usuario != null) {
                //Se inicia la sesion
                HttpSession session = request.getSession();
                //Se guardan los datos del usuario en la sesion
                session.setAttribute("usuario", usuario);
                //Se redirecciona al inicio
                response.sendRedirect(request.getContextPath()+"/index");
            } else {
                //Se envia el error de datos incorrectos
                request.setAttribute("msg", "'\u00a1Los datos ingresados son incorrectos!'");
                //Se redirecciona al login
                getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            ProductosServlet.sendInternalError(response, e);
        }
    }

}
