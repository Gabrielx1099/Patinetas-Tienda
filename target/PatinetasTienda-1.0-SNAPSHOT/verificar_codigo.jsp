<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verificación de Código</title>
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

    .login-page input[type="text"] {
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
    </style>
</head>
<body>

<jsp:include page="WEB-INF/header.jsp" />

<div class="login-page">
    <div class="container">
        <h2>Verificación de Código</h2>

        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/verificarCodigo" method="post">
            <label for="codigoIngresado">Código recibido</label>
            <input type="text" id="codigoIngresado" name="codigoIngresado" required maxlength="6" />

            <button type="submit" class="btn">Verificar</button>
        </form>

        <div class="text-center">
            ¿No recibiste el código? <a href="registro_usuario.jsp">Volver a registrarse</a>
        </div>
    </div>
</div>

</body>
</html>
<jsp:include page="WEB-INF/footer.jsp"/>