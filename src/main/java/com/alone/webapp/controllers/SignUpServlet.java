package com.alone.webapp.controllers;

import com.alone.webapp.models.Usuario;
import com.alone.webapp.DAO.UsuarioDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean methodSuccess = UsuariosServlet.addUsuario(request,usuarioDAO);
        String msg = "'\u00a1Error al registrar el usuario!'";
        if(methodSuccess){
            msg = "'\u00a1Usuario registrado con \u00e9xito!'";
        }
        request.setAttribute("msg",msg);
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request,response);
    }
}
