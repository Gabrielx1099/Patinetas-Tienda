package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Marca;
import com.mycompany.patinetastienda.MarcaDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditarMarcaServlet")
public class EditarMarcaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nombre = request.getParameter("nombre");
        String idParam = request.getParameter("id");

        try {
            // Validación del nombre
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("El nombre de la marca no puede estar vacío.");
            }

            // Validación del ID
            int id;
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                throw new Exception("ID de marca inválido.");
            }

            // Crear objeto marca
            Marca marca = new Marca();
            marca.setId(id);
            marca.setNombre(nombre.trim());

            // Actualizar marca
            MarcaDAO dao = new MarcaDAO();
            boolean actualizado = dao.updateMarca(marca);

            if (actualizado) {
                session.setAttribute("success", "Marca actualizada correctamente.");
            } else {
                session.setAttribute("error", "No se pudo actualizar la marca (posiblemente no existe).");
            }

        } catch (Exception e) {
            session.setAttribute("error", "Error al actualizar la marca: " + e.getMessage());
        }

        // 🔄 Redirigir al servlet que siempre recarga datos y muestra la vista
        response.sendRedirect(request.getContextPath() + "/registroMarca");
    }
}
