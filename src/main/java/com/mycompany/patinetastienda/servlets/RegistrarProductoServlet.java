package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

@WebServlet("/RegistrarProductoServlet")
@MultipartConfig
public class RegistrarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            // Obtener datos del formulario
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            double precio = Double.parseDouble(request.getParameter("precio"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            int subcategoriaId = Integer.parseInt(request.getParameter("subcategoriaId"));
            int marcaId = Integer.parseInt(request.getParameter("marcaId"));

            // Procesar imagen
            Part imagenPart = request.getPart("imagen");
            String nombreOriginal = Paths.get(imagenPart.getSubmittedFileName()).getFileName().toString();
            String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf('.'));
            String nombreImagen = UUID.randomUUID().toString() + extension;

            // Ruta de desarrollo donde se debe guardar la imagen físicamente
            String rutaBase = "C:/Users/gv250/OneDrive/Escritorio/PatinetasTienda";
            String rutaRelativa = "assets/Productos/" + nombreImagen;
            String rutaFinal = rutaBase + "/src/main/webapp/" + rutaRelativa;

            // Crear directorio si no existe
            File directorio = new File(rutaBase + "/src/main/webapp/assets/Productos");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Copiar imagen desde input a la ruta final
            try (InputStream input = imagenPart.getInputStream()) {
                Files.copy(input, Paths.get(rutaFinal), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("✅ Imagen guardada en: " + rutaFinal);
            }

            // Crear y llenar objeto Producto
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setImagenUrl(rutaRelativa); // solo ruta relativa en la BD
            producto.setCategoriaId(categoriaId);
            producto.setSubcategoriaId(subcategoriaId);
            producto.setMarcaId(marcaId);

            // Guardar en base de datos
            ProductoDAO dao = new ProductoDAO();
            int idGenerado = dao.insertProducto(producto);
            System.out.println("DEBUG: Producto registrado con ID: " + idGenerado);

            session.setAttribute("success", "Producto registrado exitosamente con ID: " + idGenerado);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Error al registrar producto: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/registroProducto");
    }
}
