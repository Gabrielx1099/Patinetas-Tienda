<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../WEB-INF/header_min_admin.jsp">
    <jsp:param name="title" value="Lista de Productos"/>
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

<!-- Bootstrap necesario para modales -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <h2 class="mb-4">Lista de Productos</h2>

            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Imagen</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Fecha</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${productos}">
                        <tr>
                            <td>${p.id}</td>
                            <td><img src="${pageContext.request.contextPath}/${p.imagenUrl}" width="60" height="60"/></td>
                            <td>${p.nombre}</td>
                            <td>S/ ${p.precio}</td>
                            <td>${p.stock}</td>
                            <td>${p.fechaCreacion}</td>
                            <td>
                                <!-- Botón para abrir el modal -->
                                <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#modalEditar${p.id}">
                                    Editar
                                </button>
                                <a href="${pageContext.request.contextPath}/EliminarProductoServlet?id=${p.id}" class="btn btn-danger btn-sm" onclick="return confirm('¿Deseas eliminar este producto?')">Eliminar</a>
                            </td>
                        </tr>

                        <!-- Modal de edición -->
                        <div class="modal fade" id="modalEditar${p.id}" tabindex="-1" aria-labelledby="modalLabel${p.id}" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form method="post" action="${pageContext.request.contextPath}/EditarProductoServlet">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="modalLabel${p.id}">Editar Producto</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                                        </div>
                                        <div class="modal-body">
                                            <input type="hidden" name="id" value="${p.id}"/>
                                            <input type="hidden" name="imagenUrl" value="${p.imagenUrl}"/>

                                            <div class="mb-3">
                                                <label>Nombre:</label>
                                                <input type="text" name="nombre" value="${p.nombre}" class="form-control" required/>
                                            </div>

                                            <div class="mb-3">
                                                <label>Descripción:</label>
                                                <textarea name="descripcion" class="form-control" required>${p.descripcion}</textarea>
                                            </div>

                                            <div class="mb-3">
                                                <label>Precio:</label>
                                                <input type="number" step="0.01" name="precio" value="${p.precio}" class="form-control" required/>
                                            </div>

                                            <div class="mb-3">
                                                <label>Stock:</label>
                                                <input type="number" name="stock" value="${p.stock}" class="form-control" required/>
                                            </div>

                                            <div class="mb-3">
                                                <label>Categoría:</label>
                                                <select name="categoriaId" class="form-select" required>
                                                    <c:forEach var="c" items="${categorias}">
                                                        <option value="${c.id}" ${c.id == p.categoriaId ? 'selected' : ''}>${c.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3">
                                                <label>Subcategoría:</label>
                                                <select name="subcategoriaId" class="form-select" required>
                                                    <c:forEach var="s" items="${subcategorias}">
                                                        <option value="${s.id}" ${s.id == p.subcategoriaId ? 'selected' : ''}>${s.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="mb-3">
                                                <label>Marca:</label>
                                                <select name="marcaId" class="form-select" required>
                                                    <c:forEach var="m" items="${marcas}">
                                                        <option value="${m.id}" ${m.id == p.marcaId ? 'selected' : ''}>${m.nombre}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../WEB-INF/footer.jsp"/>
