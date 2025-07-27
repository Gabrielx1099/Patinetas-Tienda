package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Categoria;
import com.mycompany.patinetastienda.CategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/RegistrarCategoriaServlet")
public class RegistrarCategoriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombre");

        try {
            // Validar que el nombre no sea nulo o vacío
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la categoría no puede estar vacío.");
            }

            // Crear objeto Categoria
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre.trim());

            // Registrar en la base de datos
            CategoriaDAO dao = new CategoriaDAO();
            dao.registrarCategoria(categoria);

            session.setAttribute("success", "Categoría registrada exitosamente.");

        } catch (Exception e) {
            session.setAttribute("error", "Error al registrar la categoría: " + e.getMessage());
        }

        // Redirigir al servlet o página que muestra la vista actualizada
        response.sendRedirect(request.getContextPath() + "/registroCategoria");
    }
}
