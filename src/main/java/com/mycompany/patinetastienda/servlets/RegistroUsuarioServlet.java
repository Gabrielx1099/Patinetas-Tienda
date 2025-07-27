package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/registroUsuario")
public class RegistroUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido"); // 👈 Nuevo campo
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmarPassword = request.getParameter("confirmar_password");
        String genero = request.getParameter("genero");
        String telefono = request.getParameter("telefono");
        String rol = "cliente"; // Rol fijo por seguridad

        // Validaciones básicas
        if (nombre == null || apellido == null || email == null || password == null || confirmarPassword == null ||
            genero == null || telefono == null ||
            nombre.trim().isEmpty() || apellido.trim().isEmpty() || email.trim().isEmpty() ||
            password.trim().isEmpty() || confirmarPassword.trim().isEmpty() ||
            genero.trim().isEmpty() || telefono.trim().isEmpty()) {

            request.setAttribute("error", "Por favor, complete todos los campos obligatorios.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        if (!nombre.matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ ]{2,50}$") || !apellido.matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ ]{2,50}$")) {
            request.setAttribute("error", "Nombre y apellido solo deben contener letras y espacios (2-50 caracteres).");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") || email.length() > 100) {
            request.setAttribute("error", "Formato de email inválido o muy largo.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmarPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$")) {
            request.setAttribute("error", "La contraseña debe tener entre 8 y 50 caracteres, con mayúscula, minúscula, número y carácter especial.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido); // 👈 Aquí se setea
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(hashedPassword);
        nuevoUsuario.setRol(rol);
        nuevoUsuario.setFechaRegistro(java.time.LocalDate.now().toString());
        nuevoUsuario.setGenero(genero);
        nuevoUsuario.setTelefono(telefono);

        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            if (usuarioDao.getUsuarioByEmail(email) != null) {
                request.setAttribute("error", "El email ya está registrado.");
                request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
                return;
            }

            int idGenerado = usuarioDao.insertUsuario(nuevoUsuario);

            if (idGenerado > 0) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            } else {
                request.setAttribute("error", "No se pudo registrar el usuario.");
                request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de base de datos: " + e.getMessage());
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
    }
}
