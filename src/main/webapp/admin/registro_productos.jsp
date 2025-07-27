<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../WEB-INF/header_min_admin.jsp">
    <jsp:param name="title" value="Registrar Producto"/>
</jsp:include>
<style>
    body {
        background-color: #F0EFEF; /* Fondo gris claro más definido */
        color: #1A1A1A;
    }

    .form-container {
        background-color: #FFF5EB; /* Suave naranja crema */
        padding: 30px;
        border-radius: 14px;
        box-shadow: 0 6px 25px rgba(0, 0, 0, 0.15);
        border: 1px solid #F0D1B8;
    }

    h2 {
        color: #D35400;
        border-bottom: 2px solid #E67E22;
        padding-bottom: 12px;
        margin-bottom: 30px;
        font-weight: bold;
    }

    .form-label {
        font-weight: 600;
        color: #333333;
    }

    .form-control, .form-select {
        background-color: #FFFFFF;
        border: 1px solid #E67E22;
        color: #1A1A1A;
        transition: all 0.3s ease;
    }

    .form-control:focus, .form-select:focus {
        border-color: #E67E22;
        box-shadow: 0 0 8px rgba(230, 126, 34, 0.35);
    }

    .btn-success {
        background: linear-gradient(45deg, #F97B22, #F65800); /* Naranja intenso pastel */
        color: #FFFFFF;
        border: none;
        font-weight: bold;
        padding: 12px 28px;
        transition: all 0.3s ease-in-out;
        box-shadow: 0 6px 15px rgba(246, 88, 0, 0.4);
    }

    .btn-success:hover {
        background: linear-gradient(45deg, #F65800, #C74300); /* Más fuerte al hover */
        transform: translateY(-2px);
        box-shadow: 0 8px 22px rgba(199, 67, 0, 0.5);
    }

    .alert {
        font-weight: bold;
        padding: 10px 20px;
        border-radius: 6px;
    }

    .alert-success {
        background-color: #D1F2EB;
        color: #117A65;
        border: 1px solid #A3E4D7;
    }

    .alert-danger {
        background-color: #FADBD8;
        color: #C0392B;
        border: 1px solid #F5B7B1;
    }
</style>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <div class="col-md-9">
            <div class="form-container">
                <h2>Registrar Nuevo Producto</h2>

                <c:if test="${not empty success}">
                    <div class="alert alert-success">${success}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form action="${pageContext.request.contextPath}/RegistrarProductoServlet" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" name="nombre" id="nombre" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción:</label>
                        <textarea name="descripcion" id="descripcion" class="form-control" rows="3"></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="precio" class="form-label">Precio:</label>
                        <input type="number" step="0.01" name="precio" id="precio" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="stock" class="form-label">Stock:</label>
                        <input type="number" name="stock" id="stock" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="categoria" class="form-label">Categoría:</label>
                        <select name="categoriaId" id="categoria" class="form-select" required>
                            <option value="">Seleccione una categoría</option>
                            <c:forEach var="c" items="${categorias}">
                                <option value="${c.id}">${c.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="subcategoria" class="form-label">Subcategoría:</label>
                        <select name="subcategoriaId" id="subcategoria" class="form-select" required>
                            <option value="">Seleccione una subcategoría</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="marca" class="form-label">Marca:</label>
                        <select name="marcaId" id="marca" class="form-select" required>
                            <option value="">Seleccione una marca</option>
                            <c:forEach var="m" items="${marcas}">
                                <option value="${m.id}">${m.nombre}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="imagen" class="form-label">Imagen:</label>
                        <input type="file" name="imagen" id="imagen" class="form-control" accept="image/*" required>
                    </div>

                    <button type="submit" class="btn btn-success">Registrar Producto</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById("categoria").addEventListener("change", function () {
        const categoriaId = this.value;
        const subcategoriaSelect = document.getElementById("subcategoria");

        subcategoriaSelect.innerHTML = '<option value="">Cargando subcategorías...</option>';

        fetch('${pageContext.request.contextPath}/SubcategoriasPorCategoriaServlet?categoriaId=' + categoriaId)
            .then(res => {
                if (!res.ok) {
                    throw new Error("No se pudo obtener respuesta del servidor");
                }
                return res.json();
            })
            .then(data => {
                console.log("Subcategorías recibidas:", data);
                subcategoriaSelect.innerHTML = '<option value="">Seleccione una subcategoría</option>';
                data.forEach(sub => {
                    const option = document.createElement("option");
                    option.value = sub.id;
                    option.textContent = sub.nombre;
                    subcategoriaSelect.appendChild(option);
                });
            })
            .catch(err => {
                console.error("Error al cargar subcategorías:", err);
                subcategoriaSelect.innerHTML = '<option value="">Error al cargar subcategorías</option>';
            });
    });
</script>

<jsp:include page="../WEB-INF/footer.jsp"/>
