package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Carrito;
import com.mycompany.patinetastienda.CarritoDAO;
import com.mycompany.patinetastienda.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AñadirCarritoServlet")
public class AñadirCarritoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = usuario.getId();
        int productoId = Integer.parseInt(request.getParameter("productoId"));

        Carrito carrito = new Carrito();
        carrito.setUsuarioId(usuarioId);
        carrito.setProductoId(productoId);
        carrito.setCantidad(1); // cantidad inicial

        try {
            CarritoDAO carritoDAO = new CarritoDAO();

            // Si el producto ya está en el carrito, actualizamos la cantidad
            Carrito existente = carritoDAO.getCarritoByUsuarioAndProducto(usuarioId, productoId);
            if (existente != null) {
                existente.setCantidad(existente.getCantidad() + 1);
                carritoDAO.updateCarrito(existente);
            } else {
                carritoDAO.insertCarrito(carrito);
            }

            // Redirige al carrito para ver los productos
            response.sendRedirect(request.getContextPath() + "/verCarrito");

        } catch (SQLException e) {
            throw new ServletException("Error al añadir al carrito", e);
        }
    }
}
