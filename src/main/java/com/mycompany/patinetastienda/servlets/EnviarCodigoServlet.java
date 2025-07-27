package com.mycompany.patinetastienda.servlets;

import com.mycompany.patinetastienda.Usuario;
import com.mycompany.patinetastienda.util.CodigoUtil;
import com.mycompany.patinetastienda.util.EmailUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/enviarCodigo")
public class EnviarCodigoServlet extends HttpServlet {
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

        if (nombre == null || apellido == null || email == null || password == null || confirmarPassword == null ||
            genero == null || telefono == null ||
            nombre.trim().isEmpty() || apellido.trim().isEmpty() || email.trim().isEmpty() ||
            password.trim().isEmpty() || confirmarPassword.trim().isEmpty() ||
            genero.trim().isEmpty() || telefono.trim().isEmpty() ||
            !password.equals(confirmarPassword)) {

            request.setAttribute("error", "Datos inválidos o incompletos.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
            return;
        }

        // Crear objeto usuario temporal
        Usuario usuarioTemp = new Usuario();
        usuarioTemp.setNombre(nombre);
        usuarioTemp.setApellido(apellido); // 👈 Aquí se setea
        usuarioTemp.setEmail(email);
        usuarioTemp.setPassword(password); // aún sin encriptar
        usuarioTemp.setGenero(genero);
        usuarioTemp.setTelefono(telefono);
        usuarioTemp.setRol("cliente");
        usuarioTemp.setFechaRegistro(LocalDate.now().toString());

        // Generar código y guardar en sesión
        String codigo = CodigoUtil.generarCodigo(6);
        HttpSession session = request.getSession();
        session.setAttribute("codigoVerificacion", codigo);
        session.setAttribute("usuarioTemp", usuarioTemp); // Incluye apellido

        try {
            EmailUtil.enviarCodigoVerificacion(email, codigo);
            response.sendRedirect("verificar_codigo.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "No se pudo enviar el correo.");
            request.getRequestDispatcher("registro_usuario.jsp").forward(request, response);
        }
    }
}
