package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Marca;
import com.mycompany.patinetastienda.MarcaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/registroMarca")
public class MostrarMarcasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MarcaDAO dao = new MarcaDAO();

        try {
            // Verifica si hay un ID para editar
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    Marca marca = dao.getMarcaById(id);
                    if (marca != null) {
                        request.setAttribute("marca", marca);
                    } else {
                        request.setAttribute("error", "La marca con ID " + id + " no existe.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID inválido: " + idStr);
                }
            }

            // Cargar todas las marcas
            List<Marca> marcas = dao.getAllMarcas();
            request.setAttribute("marcas", marcas);

            // ✅ Recuperar mensajes de sesión y pasarlos como atributos
            String successMsg = (String) session.getAttribute("success");
            String errorMsg = (String) session.getAttribute("error");

            if (successMsg != null) {
                request.setAttribute("success", successMsg);
                session.removeAttribute("success"); // ⚠ se elimina para que no se repita
            }

            if (errorMsg != null) {
                request.setAttribute("error", errorMsg);
                session.removeAttribute("error");
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar datos: " + e.getMessage());
        }

        // Enviar a la vista
        request.getRequestDispatcher("/admin/registro_marca.jsp").forward(request, response);
    }
}
