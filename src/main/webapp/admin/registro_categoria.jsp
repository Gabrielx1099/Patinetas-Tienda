<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../WEB-INF/header_min_admin.jsp">
    <jsp:param name="title" value="Gestión de Categorías"/>
</jsp:include>
<style>
    body {
        background-color: #FAF7F2;
        color: #1A1A1A;
    }

    h2, h3 {
        color: #D35400;
        border-bottom: 2px solid #E67E22;
        padding-bottom: 12px;
        margin-bottom: 25px;
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
    }

    .btn-warning:hover {
        background-color: #F1C40F;
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
            <h2 class="mb-4">${not empty categoria ? 'Editar Categoría' : 'Registrar Nueva Categoría'}</h2>

            <!-- Mensajes -->
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <!-- Formulario superior: solo aparece si NO estás editando -->
            <c:if test="${empty categoria}">
                <form action="${pageContext.request.contextPath}/RegistrarCategoriaServlet" method="post" class="mb-4">
                    <div class="row g-3 align-items-center">
                        <div class="col-auto">
                            <label for="nombre" class="col-form-label">Nombre de Categoría:</label>
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

            <!-- Listado -->
            <h3>Listado de Categorías</h3>
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty categorias}">
                            <c:forEach var="c" items="${categorias}">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty categoria && categoria.id == c.id}">
                                                <form action="${pageContext.request.contextPath}/EditarCategoriaServlet" method="post" class="d-flex">
                                                    <input type="hidden" name="id" value="${c.id}" />
                                                    <input type="text" name="nombre" class="form-control form-control-sm me-2"
                                                           value="${c.nombre}" required maxlength="100" />
                                                    <button type="submit" class="btn btn-sm btn-warning me-1">Guardar</button>
                                                    <a href="${pageContext.request.contextPath}/registroCategoria" class="btn btn-sm btn-secondary">Cancelar</a>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                ${c.nombre}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:if test="${empty categoria || categoria.id != c.id}">
                                            <a href="${pageContext.request.contextPath}/registroCategoria?id=${c.id}" class="btn btn-sm btn-primary">Editar</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" class="text-center">No se encontraron categorías registradas.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
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
