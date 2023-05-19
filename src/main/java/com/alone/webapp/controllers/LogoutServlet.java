package com.alone.webapp.controllers;

import com.alone.webapp.models.Usuario;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se obtiene la sesion
        HttpSession session = request.getSession();
        //Se obtienen los datos del usuario
        Usuario usuario = (Usuario)session.getAttribute("usuario");
        if(usuario!=null){
            //Se invalida la sesion
            session.invalidate();
        }
        //Se redirecciona al inicio
        response.sendRedirect(request.getContextPath()+"/index");
    }

}
