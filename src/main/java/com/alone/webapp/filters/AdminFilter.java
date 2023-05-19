package com.alone.webapp.filters;

import com.alone.webapp.models.Usuario;
import com.alone.webapp.util.Util;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter({"/dashboard","/categorias","/usuarios","/ordenes","/productos"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Usuario usuarioSesion = Util.getSessionUser((HttpServletRequest) request);

        try {
            if (usuarioSesion != null && usuarioSesion.getNivel() == 1) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, ¡no estás autorizado para ver esta página!");
            }
        } catch (NullPointerException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, ¡necesitas iniciar sesión para ver esta página!");
        }
    }
}
