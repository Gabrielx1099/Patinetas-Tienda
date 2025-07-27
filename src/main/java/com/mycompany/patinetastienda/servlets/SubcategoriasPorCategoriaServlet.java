package com.mycompany.patinetastienda.servlets;

import com.google.gson.Gson;
import com.mycompany.patinetastienda.Subcategoria;
import com.mycompany.patinetastienda.SubcategoriaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/SubcategoriasPorCategoriaServlet")
public class SubcategoriasPorCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoriaIdParam = request.getParameter("categoriaId");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (categoriaIdParam == null || categoriaIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID de categoría no proporcionado\"}");
            return;
        }

        try {
            int categoriaId = Integer.parseInt(categoriaIdParam);

            SubcategoriaDAO dao = new SubcategoriaDAO();
            List<Subcategoria> subcategorias = dao.getSubcategoriasByCategoria(categoriaId);

            System.out.println("✅ Subcategorías para categoría ID " + categoriaId + ": " + subcategorias.size());

            String json = new Gson().toJson(subcategorias);
            response.getWriter().write(json);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"ID de categoría inválido\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"No se pudieron cargar las subcategorías.\"}");
        }
    }
}
