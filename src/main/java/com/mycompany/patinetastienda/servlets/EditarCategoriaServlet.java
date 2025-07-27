package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Categoria;
import com.mycompany.patinetastienda.CategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditarCategoriaServlet")
public class EditarCategoriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombre");
        String idParam = request.getParameter("id");

        try {
            // Validación del nombre
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la categoría no puede estar vacío.");
            }

            // Validación del ID
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                throw new Exception("ID de categoría inválido.");
            }

            // Crear objeto categoría
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nombre.trim());

            // Actualizar categoría
            CategoriaDAO dao = new CategoriaDAO();
            boolean actualizado = dao.updateCategoria(categoria);

            if (actualizado) {
                session.setAttribute("success", "Categoría actualizada correctamente.");
            } else {
                session.setAttribute("error", "No se pudo actualizar la categoría (posiblemente no existe).");
            }

        } catch (Exception e) {
            session.setAttribute("error", "Error al actualizar la categoría: " + e.getMessage());
        }

        // Redirigir al servlet o página que muestra la vista actualizada
        response.sendRedirect(request.getContextPath() + "/registroCategoria");
    }
}
