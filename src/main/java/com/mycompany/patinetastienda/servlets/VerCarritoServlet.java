package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/verCarrito")
public class VerCarritoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = usuario.getId();

        CarritoDAO carritoDAO = new CarritoDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        List<CarritoDetalle> detalles = new ArrayList<>();
        double totalCarrito = 0.0;

        try {
            List<Carrito> carritoItems = carritoDAO.getCarritosByUsuarioId(usuarioId);

            for (Carrito item : carritoItems) {
                Producto producto = productoDAO.getProductoById(item.getProductoId());
                if (producto != null) {
                    CarritoDetalle detalle = new CarritoDetalle();
                    detalle.setCarritoId(item.getId());
                    detalle.setProductoId(producto.getId());
                    detalle.setNombreProducto(producto.getNombre());
                    detalle.setImagenUrl(producto.getImagenUrl());
                    detalle.setPrecio(producto.getPrecio());
                    detalle.setCantidad(item.getCantidad());
                    detalle.setSubtotal(producto.getPrecio() * item.getCantidad());

                    totalCarrito += detalle.getSubtotal();
                    detalles.add(detalle);
                }
            }

            request.setAttribute("carritoDetalles", detalles);
            request.setAttribute("totalCarrito", totalCarrito);
            request.getRequestDispatcher("verCarrito.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al obtener carrito", e);
        }
    }
}


