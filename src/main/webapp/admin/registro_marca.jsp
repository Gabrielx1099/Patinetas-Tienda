<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../WEB-INF/header_min_admin.jsp">
    <jsp:param name="title" value="Gestión de Marcas"/>
</jsp:include>

<style>
    body {
        background-color: #FAF7F2; /* Gris claro cálido */
        color: #1A1A1A;
    }

    .form-container {
        background-color: #FFF1E6; /* Fondo suave naranja pastel */
        padding: 25px;
        border-radius: 14px;
        box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
        margin-bottom: 30px;
        border: 1px solid #F8D4B0;
    }

    h2, h3 {
        color: #D35400;
        border-bottom: 2px solid #E67E22;
        padding-bottom: 12px;
        font-weight: bold;
    }

    .form-control {
        background-color: #FFFFFF;
        border: 1px solid #E67E22;
        color: #1A1A1A;
        transition: all 0.3s ease;
    }

    .form-control:focus {
        border-color: #E67E22;
        box-shadow: 0 0 6px rgba(230, 126, 34, 0.3);
    }

    .btn-primary {
        background: linear-gradient(45deg, #FF914D, #F86D04);
        color: white;
        font-weight: bold;
        border: none;
        box-shadow: 0 4px 12px rgba(248, 109, 4, 0.3);
        transition: all 0.3s ease-in-out;
    }

    .btn-primary:hover {
        background: linear-gradient(45deg, #F86D04, #D35400);
        transform: translateY(-2px);
        box-shadow: 0 6px 18px rgba(211, 84, 0, 0.4);
    }

    .btn-warning {
        background-color: #F7DC6F;
        border: none;
        color: #1A1A1A;
        font-weight: bold;
        transition: all 0.2s ease-in-out;
    }

    .btn-warning:hover {
        background-color: #F1C40F;
        color: #1A1A1A;
    }

    .btn-secondary {
        background-color: #7F8C8D;
        color: white;
        font-weight: bold;
        border: none;
    }

    .btn-secondary:hover {
        background-color: #626E70;
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
        padding: 10px 20px;
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


<div class="container-fluid mt-4">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <div class="form-container">
                <h2>${not empty marca ? 'Editar Marca' : 'Registrar Nueva Marca'}</h2>

                <!-- Mensajes -->
                <c:if test="${not empty success}">
                    <div class="alert alert-success">${success}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <!-- Formulario superior: solo si NO estás editando -->
                <c:if test="${empty marca}">
                    <form action="${pageContext.request.contextPath}/RegistrarMarcaServlet" method="post" class="mb-4">
                        <div class="row g-3 align-items-center">
                            <div class="col-auto">
                                <label for="nombre" class="col-form-label">Nombre de Marca:</label>
                            </div>
                            <div class="col-auto">
                                <input type="text" id="nombre" name="nombre" required maxlength="100" class="form-control" />
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary">Registrar</button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>

            <!-- Listado -->
            <div class="form-container">
                <h3>Listado de Marcas</h3>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty marcas}">
                                <c:forEach var="m" items="${marcas}">
                                    <tr>
                                        <td>${m.id}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${not empty marca && marca.id == m.id}">
                                                    <form action="${pageContext.request.contextPath}/EditarMarcaServlet" method="post" class="d-flex">
                                                        <input type="hidden" name="id" value="${m.id}" />
                                                        <input type="text" name="nombre" class="form-control form-control-sm me-2"
                                                               value="${m.nombre}" required maxlength="100" />
                                                        <button type="submit" class="btn btn-sm btn-warning me-1">Guardar</button>
                                                        <a href="${pageContext.request.contextPath}/registroMarca" class="btn btn-sm btn-secondary">Cancelar</a>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    ${m.nombre}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${empty marca || marca.id != m.id}">
                                                <a href="${pageContext.request.contextPath}/registroMarca?id=${m.id}" class="btn btn-sm btn-primary">Editar</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="3" class="text-center">No se encontraron marcas registradas.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Script para ocultar mensajes después de 5 segundos -->
<script>
    setTimeout(function () {
        const success = document.querySelector('.alert-success');
        const error = document.querySelector('.alert-danger');
        if (success) success.style.display = 'none';
        if (error) error.style.display = 'none';
    }, 5000);
</script>

<jsp:include page="../WEB-INF/footer.jsp"/>
