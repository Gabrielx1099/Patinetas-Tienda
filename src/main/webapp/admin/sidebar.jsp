<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .list-group {
        background-color: #FFFFFF;
        border-radius: 10px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        overflow: hidden;
    }

    .list-group-item {
        background-color: #FFFFFF;
        color: #1A1A1A;
        font-weight: 500;
        border: none;
        padding: 15px 20px;
        transition: background-color 0.3s, color 0.3s;
    }

    .list-group-item i {
        margin-right: 8px;
        color: #F39C12; /* Naranja pastel fuerte */
    }

    .list-group-item:hover,
    .list-group-item:focus {
        background-color: #FFF3E0; /* Naranja pastel claro */
        color: #1A1A1A;
        font-weight: bold;
        border-left: 4px solid #F39C12;
    }

    .list-group-item.active {
        background-color: #F8B400;
        color: #FFFFFF;
        font-weight: bold;
    }
</style>

<div class="list-group">
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="list-group-item list-group-item-action">
        <i class="fas fa-home"></i> Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/admin/perfil.jsp" class="list-group-item list-group-item-action">
        <i class="fas fa-user"></i> Mi Perfil
    </a>

    <a href="${pageContext.request.contextPath}/registroProducto" class="list-group-item list-group-item-action">
        <i class="fas fa-box"></i> Productos
    </a>

    <a href="${pageContext.request.contextPath}/productos" class="list-group-item list-group-item-action">
        <i class="fas fa-list"></i> Lista Productos
    </a>

    <a href="${pageContext.request.contextPath}/registroCategoria" class="list-group-item list-group-item-action">
        <i class="fas fa-tag"></i> Categorías
    </a>

    <a href="${pageContext.request.contextPath}/registroSubcategoria" class="list-group-item list-group-item-action">
        <i class="fas fa-tags"></i> Subcategorías
    </a>

    <a href="${pageContext.request.contextPath}/registroMarca" class="list-group-item list-group-item-action">
        <i class="fas fa-industry"></i> Marca
    </a>

    <a href="${pageContext.request.contextPath}/admin/registro_admin.jsp" class="list-group-item list-group-item-action">
        <i class="fas fa-user-shield"></i> Registro Admin
    </a>
</div>
