package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/actualizarPerfil")
public class ActualizarPerfilServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.getWriter().write("{\"exito\":false,\"mensaje\":\"Debe iniciar sesión.\"}");
            return;
        }

        String nombre = request.getParameter("nombre");
String apellido = request.getParameter("apellido");
String email = request.getParameter("email");
String telefono = request.getParameter("telefono");
String genero = request.getParameter("genero");
String nuevaPassword = request.getParameter("password");

if (nombre == null || nombre.trim().isEmpty() ||
    apellido == null || apellido.trim().isEmpty() ||
    email == null || email.trim().isEmpty() ||
    genero == null || genero.trim().isEmpty() ||
    telefono == null || telefono.trim().isEmpty()) {

    response.getWriter().write("{\"exito\":false,\"mensaje\":\"Complete todos los campos obligatorios.\"}");
    return;
}


        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setGenero(genero);

        // Si se cambia la contraseña, encriptarla
        if (nuevaPassword != null && !nuevaPassword.trim().isEmpty()) {
            if (!nuevaPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,50}$")) {
                response.getWriter().write("{\"exito\":false,\"mensaje\":\"La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.\"}");
                return;
            }
            String hashed = BCrypt.hashpw(nuevaPassword, BCrypt.gensalt());
            usuario.setPassword(hashed);
        }

        try {
            UsuarioDAO dao = new UsuarioDAO();
            boolean actualizado = dao.updateUsuario(usuario); // ✅ CORREGIDO AQUÍ

            if (actualizado) {
                session.setAttribute("usuario", usuario); // Actualizar la sesión también
                response.getWriter().write("{\"exito\":true,\"mensaje\":\"Perfil actualizado correctamente.\"}");
            } else {
                response.getWriter().write("{\"exito\":false,\"mensaje\":\"No se pudo actualizar el perfil.\"}");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("{\"exito\":false,\"mensaje\":\"Error en el servidor: " + e.getMessage() + "\"}");
        }
    }
}
