package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Subcategoria;
import com.mycompany.patinetastienda.SubcategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/RegistrarSubcategoriaServlet")
public class RegistrarSubcategoriaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombre");
        String idCategoriaStr = request.getParameter("idCategoria");

        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la subcategoría no puede estar vacío.");
            }

            int idCategoria = Integer.parseInt(idCategoriaStr);

            Subcategoria sub = new Subcategoria();
            sub.setNombre(nombre.trim());
            sub.setIdCategoria(idCategoria);

            SubcategoriaDAO dao = new SubcategoriaDAO();
            dao.insertSubcategoria(sub);

            session.setAttribute("success", "Subcategoría registrada exitosamente.");
        } catch (Exception e) {
            session.setAttribute("error", "Error al registrar la subcategoría: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/registroSubcategoria");
    }
}
