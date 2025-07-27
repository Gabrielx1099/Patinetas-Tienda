<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
   <style>
   .registro-page {
    background-color: #f8f6fc;
    color: #1a1a1a;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 60px 0;
    min-height: 100vh;
}

.registro-page .container {
    max-width: 400px;
    margin: auto;
    background-color: #ffffff;
    padding: 30px;
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(112, 66, 255, 0.15);
    border: 1px solid #e0dff5;
}

.registro-page h2 {
    text-align: center;
    margin-bottom: 25px;
    color: #6a0dad;
    font-weight: bold;
}

.registro-page label {
    font-weight: bold;
    color: #4b0082;
    display: block;
    margin-bottom: 5px;
}

.registro-page input[type="text"],
.registro-page input[type="email"],
.registro-page input[type="password"],
.registro-page select {
    width: 100%;
    padding: 12px;
    margin-bottom: 20px;
    border: 1px solid #cbbce9;
    border-radius: 5px;
    background-color: #f7f3ff;
    color: #333;
    transition: border-color 0.3s;
}

.registro-page input:focus,
.registro-page select:focus {
    border-color: #a179e4;
    outline: none;
}

.registro-page .btn {
    width: 100%;
    padding: 12px;
    background-color: #6a0dad;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
    transition: background-color 0.3s;
}

.registro-page .btn:hover {
    background-color: #582c87;
}

.registro-page .alert {
    background-color: #a179e4;
    color: white;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 15px;
    text-align: center;
}

.registro-page .form-text {
    font-size: 0.9em;
    color: #555;
    margin-top: -15px;
    margin-bottom: 15px;
}

.registro-page .text-center {
    text-align: center;
    margin-top: 15px;
    font-size: 0.9em;
}

.registro-page a {
    color: #6a0dad;
    text-decoration: none;
}

.registro-page a:hover {
    color: #4b0082;
    text-decoration: underline;
}

    </style>
</head>

<body>
<jsp:include page="WEB-INF/header.jsp" />

<div class="registro-page">
    <div class="container">
        <h2>Crear cuenta</h2>

        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/enviarCodigo">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" required maxlength="50" value="${param.nombre}">

            <label for="apellido">Apellido</label>
            <input type="text" id="apellido" name="apellido" required maxlength="50" value="${param.apellido}">

            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email" required maxlength="100" value="${param.email}">

            <label for="telefono">Teléfono</label>
            <input type="text" id="telefono" name="telefono" required maxlength="20" value="${param.telefono}">

            <label for="genero">Género</label>
            <select id="genero" name="genero" required>
                <option value="">Seleccione su género</option>
                <option value="Masculino" ${param.genero == 'Masculino' ? 'selected' : ''}>Masculino</option>
                <option value="Femenino" ${param.genero == 'Femenino' ? 'selected' : ''}>Femenino</option>
                <option value="Otro" ${param.genero == 'Otro' ? 'selected' : ''}>Otro</option>
            </select>

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>
            <div class="form-text">
                Mínimo 8 caracteres, una mayúscula, una minúscula, un número y un símbolo.
            </div>

            <label for="confirmar_password">Confirmar contraseña</label>
            <input type="password" id="confirmar_password" name="confirmar_password" required>

            <button type="submit" class="btn">Registrarse</button>

            <div class="text-center mt-3">
                ¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
