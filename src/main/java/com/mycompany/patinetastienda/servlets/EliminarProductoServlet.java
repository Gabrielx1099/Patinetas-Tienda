package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.ProductoDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/EliminarProductoServlet")
public class EliminarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProductoDAO dao = new ProductoDAO();
            dao.deleteProducto(id);
            session.setAttribute("success", "Producto eliminado correctamente.");
        } catch (Exception e) {
            session.setAttribute("error", "Error al eliminar producto: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
