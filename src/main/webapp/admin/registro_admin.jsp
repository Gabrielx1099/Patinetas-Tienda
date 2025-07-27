<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Usuarios</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #FAF7F2;
            color: #1A1A1A;
        }

        h2, h4 {
            color: #D35400;
            border-bottom: 2px solid #F86D04;
            padding-bottom: 10px;
            margin-bottom: 25px;
            font-weight: bold;
        }

        .form-control, .form-select {
            background-color: #FFFFFF;
            border: 1px solid #E67E22;
            color: #1A1A1A;
            transition: all 0.3s ease;
        }

        .form-control:focus, .form-select:focus {
            border-color: #E67E22;
            box-shadow: 0 0 6px rgba(230, 126, 34, 0.3);
        }

        .btn-success {
            background: linear-gradient(45deg, #FF914D, #F86D04);
            color: white;
            font-weight: bold;
            border: none;
            box-shadow: 0 4px 12px rgba(248, 109, 4, 0.3);
            transition: all 0.3s ease-in-out;
        }

        .btn-success:hover {
            background: linear-gradient(45deg, #F86D04, #D35400);
            transform: translateY(-2px);
            box-shadow: 0 6px 18px rgba(211, 84, 0, 0.4);
        }

        .table {
            background-color: #FFFFFF;
            border: 1px solid #DDD;
        }

        .table thead {
            background-color: #F86D04;
            color: white;
        }

        .table tbody tr:hover {
            background-color: #FFF5EB;
        }

        .alert {
            font-weight: bold;
            border-radius: 6px;
        }

        .alert-success {
            background-color: #D5F5E3;
            color: #117A65;
            border: 1px solid #A3E4D7;
        }

        .alert-danger {
            background-color: #FADBD8;
            color: #C0392B;
            border: 1px solid #F5B7B1;
        }
    </style>
</head>

<body>

<jsp:include page="/WEB-INF/header.jsp" />

<div class="container-fluid mt-4">
    <div class="row">
        <!-- Sidebar izquierdo -->
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <!-- Contenido principal -->
        <div class="col-md-9">
            <h2>Registrar Usuario</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <c:if test="${not empty exito}">
                <div class="alert alert-success">${exito}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/registroAdmin">
                <div class="row mb-3">
                    <div class="col">
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required>
                    </div>
                    <div class="col">
                        <input type="text" name="apellido" class="form-control" placeholder="Apellido" required>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <input type="email" name="email" class="form-control" placeholder="Correo Electrónico" required>
                    </div>
                    <div class="col">
                        <input type="text" name="telefono" class="form-control" placeholder="Teléfono" required>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <input type="password" name="password" class="form-control" placeholder="Contraseña" required>
                    </div>
                    <div class="col">
                        <input type="password" name="confirmar_password" class="form-control" placeholder="Confirmar Contraseña" required>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col">
                        <select name="genero" class="form-select" required>
                            <option value="">Seleccione género</option>
                            <option value="masculino">Masculino</option>
                            <option value="femenino">Femenino</option>
                            <option value="otro">Otro</option>
                        </select>
                    </div>
                    <div class="col">
                        <select name="rol" class="form-select" required>
                            <option value="">Seleccione rol</option>
                            <option value="cliente">Cliente</option>
                            <option value="admin">Administrador</option>
                        </select>
                    </div>
                </div>

                <button type="submit" class="btn btn-success">Registrar Usuario</button>
            </form>

            <hr class="my-5">

            <h4>Usuarios Registrados</h4>
            <div class="table-responsive mt-3">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>Teléfono</th>
                            <th>Género</th>
                            <th>Rol</th>
                            <th>Fecha Registro</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${usuarios}">
                            <tr>
                                <td>${u.id}</td>
                                <td>${u.nombre}</td>
                                <td>${u.apellido}</td>
                                <td>${u.email}</td>
                                <td>${u.telefono}</td>
                                <td>${u.genero}</td>
                                <td>${u.rol}</td>
                                <td>${u.fechaRegistro}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> <!-- Fin del contenido principal -->
    </div> <!-- Fin de row -->
</div> <!-- Fin de container-fluid -->

<jsp:include page="/WEB-INF/footer.jsp" />
</body>
</html>
