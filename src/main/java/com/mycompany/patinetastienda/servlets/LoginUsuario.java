/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gv250
 */


@WebServlet("/login")
public class LoginUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Debe completar todos los campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();

        try {
            Usuario usuario = usuarioDao.getUsuarioByEmail(email);

            if (usuario != null) {
                boolean passwordCorrecto = BCrypt.checkpw(password, usuario.getPassword());
                System.out.println("DEBUG: Verificación de contraseña: " + passwordCorrecto);

                if (passwordCorrecto) {
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);

                    String rol = usuario.getRol() != null ? usuario.getRol().trim().toLowerCase() : "";
                    String contextPath = request.getContextPath();

                    if ("admin".equals(rol)) {
                        response.sendRedirect(contextPath + "/admin/perfil.jsp");
                    } else {
                        response.sendRedirect(contextPath + "/index.jsp");
                    }

                } else {
                    request.setAttribute("error", "Correo o contraseña incorrectos.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Correo o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.err.println("ERROR: Fallo en la base de datos: " + e.getMessage());
            throw new ServletException("Error al verificar credenciales", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
