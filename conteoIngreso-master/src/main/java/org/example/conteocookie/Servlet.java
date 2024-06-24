package org.example.conteocookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Servlet")

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el valor actual del contador de visitas del usuario
        int visitUsuario = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visitasUsuario")) {
                    visitUsuario = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        // Incrementar el contador de visitas del usuario
        visitUsuario++;

        // Actualiza el contador
        Cookie visitasCookie = new Cookie("visitasUsuario", String.valueOf(visitUsuario));
        response.addCookie(visitasCookie);

        // mensaje para mostrar
        String mensaje = "";
        if (visitUsuario == 2) {
            mensaje = "Gracias por visitar mi sitio web por segunda vez.";
        } else if (visitUsuario == 3) {
            mensaje = "Gracias por visitar mi sitio web por tercera vez.";
        } else {
            mensaje = "Gracias por seguir visitando mi sitio web. Esta es tu visita número " + visitUsuario + ".";
        }

        // Configurar la respuesta
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + mensaje + "</h1>");
        out.println("</body></html>");
    }
}
