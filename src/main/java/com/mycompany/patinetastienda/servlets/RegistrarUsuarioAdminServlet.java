package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/registroAdmin")
public class RegistrarUsuarioAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmarPassword = request.getParameter("confirmar_password");
        String genero = request.getParameter("genero");
        String telefono = request.getParameter("telefono");
        String rol = request.getParameter("rol");

        // Validaciones básicas
        if (nombre == null || apellido == null || email == null || password == null || confirmarPassword == null ||
            genero == null || telefono == null || rol == null ||
            nombre.trim().isEmpty() || apellido.trim().isEmpty() || email.trim().isEmpty() ||
            password.trim().isEmpty() || confirmarPassword.trim().isEmpty() ||
            genero.trim().isEmpty() || telefono.trim().isEmpty() || rol.trim().isEmpty()) {

            request.setAttribute("error", "Por favor, complete todos los campos obligatorios.");
            doGet(request, response);
            return;
        }

        if (!nombre.matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ ]{2,50}$") || !apellido.matches("^[A-Za-zÁáÉéÍíÓóÚúÑñ ]{2,50}$")) {
            request.setAttribute("error", "Nombre y apellido solo deben contener letras y espacios (2-50 caracteres).");
            doGet(request, response);
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") || email.length() > 100) {
            request.setAttribute("error", "Formato de email inválido o muy largo.");
            doGet(request, response);
            return;
        }

        if (!password.equals(confirmarPassword)) {
            request.setAttribute("error", "Las contraseñas no coinciden.");
            doGet(request, response);
            return;
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$")) {
            request.setAttribute("error", "La contraseña debe tener entre 8 y 50 caracteres, con mayúscula, minúscula, número y carácter especial.");
            doGet(request, response);
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
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
                doGet(request, response);
                return;
            }

            int idGenerado = usuarioDao.insertUsuario(nuevoUsuario);

            if (idGenerado > 0) {
                request.setAttribute("exito", "Usuario registrado correctamente.");
            } else {
                request.setAttribute("error", "No se pudo registrar el usuario.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de base de datos: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            UsuarioDAO usuarioDao = new UsuarioDAO();
            List<Usuario> listaUsuarios = usuarioDao.getAllUsuarios();
            request.setAttribute("usuarios", listaUsuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/admin/registro_admin.jsp").forward(request, response);
    }
}
