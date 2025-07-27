package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Producto;
import com.mycompany.patinetastienda.ProductoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/zonaCategoria")
public class ZonaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoriaParam = request.getParameter("categoria");

        if (categoriaParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoría no especificada");
            return;
        }

        int categoriaId;
        String jspDestino;

        switch (categoriaParam.toLowerCase()) {
            case "skates":
                categoriaId = 1;
                jspDestino = "/skates.jsp";
                break;
            case "tablas":
                categoriaId = 2;
                jspDestino = "/tablas.jsp";
                break;
            case "ruedas":
                categoriaId = 3;
                jspDestino = "/ruedas.jsp";
                break;
            case "accesorios":
                categoriaId = 4;
                jspDestino = "/accesorios.jsp";
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Categoría no válida");
                return;
        }

        ProductoDAO dao = new ProductoDAO();
        List<Producto> productos = dao.getProductosByCategoriaId(categoriaId);

        request.setAttribute("productos", productos);
        request.getRequestDispatcher(jspDestino).forward(request, response);
    }
}
