package com.mycompany.patinetastienda.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/header")
public class NavbarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establece un atributo (opcional)
        String title = request.getParameter("title");
        if (title != null) {
            request.setAttribute("title", title);
        }

        // Redirige al JSP con forward (no include)
        request.getRequestDispatcher("/WEB-INF/header.jsp").forward(request, response);
    }
}
