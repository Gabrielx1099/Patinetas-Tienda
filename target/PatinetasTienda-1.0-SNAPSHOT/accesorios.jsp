<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.patinetastienda.Producto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="WEB-INF/header.jsp" />

<style>
    body {
        background-color: #f4f1ed;
        color: #1a1a1a;
    }

    h2 {
        color: #333;
        border-bottom: 2px solid #FFA726;
        padding-bottom: 10px;
        margin-bottom: 30px;
    }

    #buscador {
        border: 2px solid #FFA726;
        border-radius: 8px;
        padding: 10px 20px;
        font-size: 1.1rem;
        background-color: #fffaf4;
        margin-bottom: 30px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    .card {
        border: none;
        border-radius: 12px;
        box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
        transition: transform 0.2s ease;
    }

    .card:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 20px rgba(255, 167, 38, 0.25);
    }

    .card-title {
        font-weight: bold;
        font-size: 1.2rem;
        color: #333;
    }

    .card-text {
        font-size: 0.95rem;
        color: #555;
    }

    .btn-primary {
        background: linear-gradient(45deg, #FFA726, #FB8C00);
        border: none;
        color: #fff;
        font-weight: bold;
        border-radius: 8px;
        padding: 10px 18px;
    }

    .btn-primary:hover {
        background: linear-gradient(45deg, #FB8C00, #FFA726);
        transform: translateY(-2px);
        box-shadow: 0 5px 15px rgba(255, 152, 0, 0.3);
    }

    .card-body form {
        margin-top: 15px;
        display: flex;
        justify-content: center;
    }
</style>

<div class="container mt-5">
    <h2 class="mb-4">Zona Accesorios</h2>

    <!-- Filtros -->
<input type="text" id="buscador" placeholder="Buscar producto por nombre..." class="form-control form-control-lg mb-3">

<div class="row mb-4">
    <div class="col-md-4">
        <label>Precio mínimo:</label>
        <input type="number" id="precioMin" class="form-control" placeholder="S/ mínimo">
    </div>
    <div class="col-md-4">
        <label>Precio máximo:</label>
        <input type="number" id="precioMax" class="form-control" placeholder="S/ máximo">
    </div>
    <div class="col-md-4">
        <label>Categoria:</label>
        <select id="filtroSubcategoria" class="form-control">
            <option value="">Todas</option>
        </select>
    </div>
</div>

    <c:choose>
        <c:when test="${not empty productos}">
            <div class="row mt-3">
                <c:forEach var="p" items="${productos}">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <img src="${pageContext.request.contextPath}/${p.imagenUrl}"
                                 class="card-img-top" alt="${p.nombre}"
                                 style="max-height: 200px; object-fit: contain; padding: 15px;">
                            <div class="card-body text-center">
                                <h5 class="card-title">${p.nombre}</h5>
                                <p class="card-text">
                                    <strong>Categoría:</strong> ${p.subcategoriaNombre} <br>
                                    <strong>Marca:</strong> ${p.marcaNombre} <br>
                                    <strong>Precio:</strong> S/
                                    <fmt:formatNumber value="${p.precio}" type="number" minFractionDigits="2" maxFractionDigits="2" />
                                </p>
                                <form action="AñadirCarritoServlet" method="post">
                                    <input type="hidden" name="productoId" value="${p.id}">
                                    <button type="submit" class="btn btn-primary">Añadir al Carrito</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info mt-4">No hay productos registrados en esta categoría.</div>
        </c:otherwise>
    </c:choose>
</div>

<!-- Script para búsqueda -->
<script>
    const filtroNombre = document.getElementById('buscador');
    const filtroPrecioMin = document.getElementById('precioMin');
    const filtroPrecioMax = document.getElementById('precioMax');
    const filtroSubcategoria = document.getElementById('filtroSubcategoria');

    // Llenar subcategorías únicas automáticamente
    window.addEventListener('DOMContentLoaded', () => {
        const subcategorias = new Set();
        document.querySelectorAll('.card-text').forEach(text => {
            const match = text.innerHTML.match(/<strong>Categoría:<\/strong>\s*(.*?)<br>/);
            if (match) {
                subcategorias.add(match[1].trim());
            }
        });

        subcategorias.forEach(sub => {
            const option = document.createElement('option');
            option.value = sub;
            option.textContent = sub;
            filtroSubcategoria.appendChild(option);
        });
    });

    // Escuchar cambios de filtros
    filtroNombre.addEventListener('input', filtrarProductos);
    filtroPrecioMin.addEventListener('input', filtrarProductos);
    filtroPrecioMax.addEventListener('input', filtrarProductos);
    filtroSubcategoria.addEventListener('change', filtrarProductos);

    function filtrarProductos() {
        const texto = filtroNombre.value.toLowerCase();
        const min = parseFloat(filtroPrecioMin.value) || 0;
        const max = parseFloat(filtroPrecioMax.value) || Infinity;
        const subcat = filtroSubcategoria.value;

        document.querySelectorAll('.card').forEach(card => {
            const nombre = card.querySelector('.card-title').textContent.toLowerCase();
            const precioMatch = card.querySelector('.card-text').innerHTML.match(/S\/\s*(\d+(\.\d+)?)/);
            const subcatMatch = card.querySelector('.card-text').innerHTML.match(/<strong>Categoría:<\/strong>\s*(.*?)<br>/);

            const precio = precioMatch ? parseFloat(precioMatch[1]) : 0;
            const subcategoria = subcatMatch ? subcatMatch[1].trim() : '';

            const visible = nombre.includes(texto) &&
                            precio >= min &&
                            precio <= max &&
                            (subcat === '' || subcategoria === subcat);

            card.parentElement.style.display = visible ? 'block' : 'none';
        });
    }
</script>

<jsp:include page="WEB-INF/footer.jsp" />
