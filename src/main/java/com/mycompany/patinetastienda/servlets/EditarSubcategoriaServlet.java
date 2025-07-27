package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Subcategoria;
import com.mycompany.patinetastienda.SubcategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/EditarSubcategoriaServlet")
public class EditarSubcategoriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String idCategoriaStr = request.getParameter("idCategoria");

        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la subcategoría no puede estar vacío.");
            }

            int id = Integer.parseInt(idStr);
            int idCategoria = Integer.parseInt(idCategoriaStr);

            Subcategoria sub = new Subcategoria(id, nombre.trim(), idCategoria);
            SubcategoriaDAO dao = new SubcategoriaDAO();

            boolean actualizado = dao.updateSubcategoria(sub);
            if (actualizado) {
                session.setAttribute("success", "Subcategoría actualizada correctamente.");
            } else {
                session.setAttribute("error", "No se pudo actualizar la subcategoría.");
            }

        } catch (Exception e) {
            session.setAttribute("error", "Error al actualizar la subcategoría: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/registroSubcategoria");
    }
}
