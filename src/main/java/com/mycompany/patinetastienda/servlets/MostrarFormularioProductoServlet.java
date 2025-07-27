package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/registroProducto")
public class MostrarFormularioProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            SubcategoriaDAO subDAO = new SubcategoriaDAO();
            MarcaDAO marcaDAO = new MarcaDAO();

            request.setAttribute("categorias", categoriaDAO.getAllCategorias());
            request.setAttribute("subcategorias", subDAO.getAllSubcategorias());
            request.setAttribute("marcas", marcaDAO.getAllMarcas());

            // Mensajes de sesión
            request.setAttribute("success", session.getAttribute("success"));
            request.setAttribute("error", session.getAttribute("error"));
            session.removeAttribute("success");
            session.removeAttribute("error");

        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar formulario: " + e.getMessage());
        }

        request.getRequestDispatcher("/admin/registro_productos.jsp").forward(request, response);
    }
}