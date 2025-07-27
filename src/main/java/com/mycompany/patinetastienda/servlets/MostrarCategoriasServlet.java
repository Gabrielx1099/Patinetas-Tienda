package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Categoria;
import com.mycompany.patinetastienda.CategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/registroCategoria")
public class MostrarCategoriasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CategoriaDAO dao = new CategoriaDAO();

        try {
            // Verifica si hay un ID para editar
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Categoria categoria = dao.getCategoriaById(id);
                    if (categoria != null) {
                        request.setAttribute("categoria", categoria);
                    } else {
                        request.setAttribute("error", "La categoría con ID " + id + " no existe.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID inválido: " + idStr);
                }
            }

            // Cargar todas las categorías
            List<Categoria> categorias = dao.getAllCategorias();
            request.setAttribute("categorias", categorias);

            // Mensajes desde sesión
            String successMsg = (String) session.getAttribute("success");
            String errorMsg = (String) session.getAttribute("error");

            if (successMsg != null) {
                request.setAttribute("success", successMsg);
                session.removeAttribute("success");
            }

            if (errorMsg != null) {
                request.setAttribute("error", errorMsg);
                session.removeAttribute("error");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar datos: " + e.getMessage());
        }

        // Enviar a la vista
        request.getRequestDispatcher("/admin/registro_categoria.jsp").forward(request, response);
    }
}
