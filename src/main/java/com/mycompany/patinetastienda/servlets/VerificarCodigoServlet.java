package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/verificarCodigo")
public class VerificarCodigoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigoIngresado = request.getParameter("codigoIngresado");
        HttpSession session = request.getSession();
        String codigoOriginal = (String) session.getAttribute("codigoVerificacion");

        Usuario usuario = (Usuario) session.getAttribute("usuarioTemp");

        if (codigoIngresado != null && codigoOriginal != null && codigoOriginal.equals(codigoIngresado) && usuario != null) {

            // Encriptar la contraseña antes de registrar
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            usuario.setPassword(hashedPassword);

            try {
                UsuarioDAO dao = new UsuarioDAO();
                dao.insertUsuario(usuario);

                // Limpiar sesión
                session.removeAttribute("usuarioTemp");
                session.removeAttribute("codigoVerificacion");

                response.sendRedirect("login.jsp");

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al registrar usuario: " + e.getMessage());
                request.getRequestDispatcher("verificar_codigo.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Código incorrecto.");
            request.getRequestDispatcher("verificar_codigo.jsp").forward(request, response);
        }
    }
}
