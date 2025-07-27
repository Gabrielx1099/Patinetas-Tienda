package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Carrito;
import com.mycompany.patinetastienda.CarritoDAO;
import com.mycompany.patinetastienda.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ActualizarCarritoServlet")
public class ActualizarCarritoServlet extends HttpServlet {
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
        String[] cantidades = request.getParameterValues("cantidades");
        String[] productoIds = request.getParameterValues("productoIds");

        CarritoDAO carritoDAO = new CarritoDAO();

        try {
            if (cantidades != null && productoIds != null && cantidades.length == productoIds.length) {
                for (int i = 0; i < cantidades.length; i++) {
                    int cantidad = Integer.parseInt(cantidades[i]);
                    int productoId = Integer.parseInt(productoIds[i]);

                    if (cantidad < 1) cantidad = 1;

                    Carrito carrito = carritoDAO.getCarritoByUsuarioAndProducto(usuarioId, productoId);
                    if (carrito != null) {
                        carrito.setCantidad(cantidad);
                        carritoDAO.updateCarrito(carrito);
                    }
                }
            }

            response.sendRedirect("verCarrito.jsp");

        } catch (SQLException e) {
            throw new ServletException("Error actualizando cantidades del carrito", e);
        }
    }
}
