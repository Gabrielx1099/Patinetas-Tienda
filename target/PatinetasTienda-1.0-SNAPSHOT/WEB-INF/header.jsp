<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.mycompany.patinetastienda.Usuario" %>

<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
<!-- Tu CSS personalizado -->
<link href="${pageContext.request.contextPath}/assets/css/styles.css" rel="stylesheet">

<!-- Navbar principal -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Tabla Brava</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/zonaCategoria?categoria=skates">Skates</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/zonaCategoria?categoria=tablas">Tablas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/zonaCategoria?categoria=ruedas">Ruedas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/zonaCategoria?categoria=accesorios">Accesorios</a>
                </li>
            </ul>

            <div class="navbar-nav">
                <c:choose>
                    <c:when test="${not empty sessionScope.usuario}">
                        <%
                            Usuario usuario = (Usuario) session.getAttribute("usuario");
                            String saludo = "Bienvenido";
                            String rol = "";
                            if (usuario != null) {
                                String genero = usuario.getGenero();
                                if ("femenino".equalsIgnoreCase(genero)) {
                                    saludo = "Bienvenida";
                                } else if ("otro".equalsIgnoreCase(genero)) {
                                    saludo = "Bienvenide";
                                }
                                rol = usuario.getRol(); // asumimos que existe este método
                            }
                        %>
                        <span class="nav-item nav-link text-white">
                            <%= saludo %>, <%= usuario.getNombre() %>
                        </span>
                        <%
                            if ("admin".equalsIgnoreCase(rol)) {
                        %>
                            <a class="nav-link" href="${pageContext.request.contextPath}/admin/perfil.jsp">Administración</a>
                        <%
                            } else {
                        %>
                            <a class="nav-link" href="${pageContext.request.contextPath}/usuario/perfil.jsp">Mi Perfil</a>
                        <%
                            }
                        %>
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Cerrar Sesión</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Iniciar Sesión</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/registro_usuario.jsp">Registrarse</a>
                    </c:otherwise>
                        
                </c:choose>
            </div>
        </div>
    </div>
</nav>
