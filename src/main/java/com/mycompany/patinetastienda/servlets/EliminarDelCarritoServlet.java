package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Carrito;
import com.mycompany.patinetastienda.CarritoDAO;
import com.mycompany.patinetastienda.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EliminarDelCarritoServlet")
public class EliminarDelCarritoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int carritoId = Integer.parseInt(request.getParameter("carritoId"));
        CarritoDAO carritoDAO = new CarritoDAO();

        try {
            carritoDAO.deleteCarrito(carritoId);
        } catch (SQLException e) {
            throw new ServletException("Error al eliminar del carrito", e);
        }

        response.sendRedirect("verCarrito"); // 🔁 vuelve a cargar desde base de datos
    }
}
