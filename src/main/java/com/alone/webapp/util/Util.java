package com.alone.webapp.util;

import com.alone.webapp.models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static Usuario getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Usuario) session.getAttribute("usuario");
    }

    public static String getRequestBody (HttpServletRequest request) throws IOException {
        // Obtén el cuerpo de la solicitud
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
