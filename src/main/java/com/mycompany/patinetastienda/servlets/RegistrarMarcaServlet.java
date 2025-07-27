package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Marca;
import com.mycompany.patinetastienda.MarcaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/RegistrarMarcaServlet")
public class RegistrarMarcaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombre");

        try {
            // Validar que el nombre no sea nulo o vacío
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la marca no puede estar vacío.");
            }

            // Crear objeto Marca
            Marca marca = new Marca();
            marca.setNombre(nombre.trim());

            // Registrar en la base de datos
            MarcaDAO dao = new MarcaDAO();
            dao.registrarMarca(marca);

            session.setAttribute("success", "Marca registrada exitosamente.");

        } catch (Exception e) {
            session.setAttribute("error", "Error al registrar la marca: " + e.getMessage());
        }

        // Redirigir al servlet que muestra la vista actualizada
        response.sendRedirect(request.getContextPath() + "/registroMarca");
    }
}
