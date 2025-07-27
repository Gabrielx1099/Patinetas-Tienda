package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/EditarProductoServlet")
public class EditarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String imagenUrl = request.getParameter("imagenUrl"); // No se cambia la imagen
            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            int subcategoriaId = Integer.parseInt(request.getParameter("subcategoriaId"));
            int marcaId = Integer.parseInt(request.getParameter("marcaId"));

            Producto producto = new Producto();
            producto.setId(id);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setImagenUrl(imagenUrl);
            producto.setCategoriaId(categoriaId);
            producto.setSubcategoriaId(subcategoriaId);
            producto.setMarcaId(marcaId);

            ProductoDAO dao = new ProductoDAO();
            dao.updateProducto(producto);

            session.setAttribute("success", "Producto actualizado correctamente.");
        } catch (Exception e) {
            session.setAttribute("error", "Error al actualizar producto: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
