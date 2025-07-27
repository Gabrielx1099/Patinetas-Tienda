package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Categoria;
import com.mycompany.patinetastienda.CategoriaDAO;
import com.mycompany.patinetastienda.Subcategoria;
import com.mycompany.patinetastienda.SubcategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/registroSubcategoria")
public class MostrarFormularioSubcategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            SubcategoriaDAO subDAO = new SubcategoriaDAO();

            List<Categoria> categorias = categoriaDAO.getAllCategorias();
            List<Subcategoria> subcategorias = subDAO.getAllSubcategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("subcategorias", subcategorias);

            // Si está editando una subcategoría
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                int id = Integer.parseInt(idStr);
                Subcategoria sub = subDAO.getSubcategoriaById(id);
                if (sub != null) {
                    request.setAttribute("subcategoria", sub);
                } else {
                    request.setAttribute("error", "La subcategoría con ID " + id + " no existe.");
                }
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar datos: " + e.getMessage());
        }

        // Recuperar mensajes
        String success = (String) session.getAttribute("success");
        String error = (String) session.getAttribute("error");

        if (success != null) {
            request.setAttribute("success", success);
            session.removeAttribute("success");
        }

        if (error != null) {
            request.setAttribute("error", error);
            session.removeAttribute("error");
        }

        request.getRequestDispatcher("/admin/registro_subcategoria.jsp").forward(request, response);
    }
}
