<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
    <style>
    .login-page {
        background-color: #f8f6fc;
        color: #1a1a1a;
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        padding: 60px 0;
        min-height: 100vh;
    }

    .login-page .container {
        max-width: 400px;
        margin: auto;
        background-color: #ffffff;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 20px rgba(112, 66, 255, 0.15);
        border: 1px solid #e0dff5;
    }

    .login-page h2 {
        text-align: center;
        margin-bottom: 25px;
        color: #6a0dad;
        font-weight: bold;
    }

    .login-page label {
        font-weight: bold;
        color: #4b0082;
        display: block;
        margin-bottom: 5px;
    }

    .login-page input[type="email"],
    .login-page input[type="password"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border: 1px solid #cbbce9;
        border-radius: 5px;
        background-color: #f7f3ff;
        color: #333;
        transition: border-color 0.3s;
    }

    .login-page input:focus {
        border-color: #a179e4;
        outline: none;
    }

    .login-page .btn {
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

    .login-page .btn:hover {
        background-color: #582c87;
    }

    .login-page .alert {
        background-color: #a179e4;
        color: white;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
    }

    .login-page .text-center {
        text-align: center;
        margin-top: 15px;
        font-size: 0.9em;
    }

    .login-page a {
        color: #6a0dad;
        text-decoration: none;
    }

    .login-page a:hover {
        color: #4b0082;
        text-decoration: underline;
    }
    </style>
</head>
<body>

<jsp:include page="WEB-INF/header.jsp" />

<div class="login-page">
    <div class="container">
        <h2>Iniciar Sesión</h2>

        <!-- Mostrar errores si los hay -->
        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>

        <!-- Formulario de login -->
        <form method="post" action="${pageContext.request.contextPath}/login">
            <label for="email">Correo electrónico</label>
            <input type="email" id="email" name="email" required maxlength="100" />

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required maxlength="50" />

            <button type="submit" class="btn">Ingresar</button>
        </form>

        <div class="text-center">
            ¿No tienes cuenta? <a href="registro_usuario.jsp">Regístrate aquí</a>
        </div>
    </div>
</div>

</body>
</html>
<jsp:include page="WEB-INF/footer.jsp"/>