<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="WEB-INF/header.jsp" />

<div class="container mt-5">
    <h2 class="mb-4">Mi Carrito</h2>

    <c:choose>
        <c:when test="${empty carritoDetalles}">
            <div class="alert alert-info">Tu carrito está vacío.</div>
        </c:when>
        <c:otherwise>
            <form action="ActualizarCarritoServlet" method="post">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Imagen</th>
                            <th>Producto</th>
                            <th>Precio Unitario</th>
                            <th>Cantidad</th>
                            <th>Subtotal</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${carritoDetalles}">
                            <tr>
                                <td style="width: 120px;">
                                    <img src="${pageContext.request.contextPath}/${item.imagenUrl}" alt="${item.nombreProducto}" class="img-fluid rounded" style="max-height: 80px;">
                                </td>
                                <td>${item.nombreProducto}</td>
                                <td>S/ <fmt:formatNumber value="${item.precio}" minFractionDigits="2" /></td>
                                <td>
                                    <input type="number" name="cantidades" value="${item.cantidad}" min="1" class="form-control" style="width: 80px;" />
                                    <input type="hidden" name="productoIds" value="${item.productoId}" />
                                </td>
                                <td>S/ <fmt:formatNumber value="${item.subtotal}" minFractionDigits="2" /></td>
                                <td>
                                   
<form action="EliminarDelCarritoServlet" method="post" class="d-inline">
    <input type="hidden" name="carritoId" value="${item.carritoId}" />
    <button class="btn btn-sm btn-danger" type="submit">Eliminar</button>
</form>


                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="d-flex justify-content-between align-items-center mt-4">
                    <div>
                        <button type="submit" class="btn btn-warning">Actualizar Cantidades</button>
                    </div>
                    <div class="text-end">
                        <h5>Total: S/ 
                            <fmt:formatNumber value="${totalCarrito}" minFractionDigits="2" />
                        </h5>
                    </div>
                </div>
            </form>

            <div class="d-flex justify-content-end mt-4 gap-3">
                <form action="VaciarCarritoServlet" method="post">
                    <button class="btn btn-danger">Vaciar Carrito</button>
                </form>
                <form action="ConfirmarCompraServlet" method="post">
                    <button class="btn btn-success">Confirmar Compra</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="WEB-INF/footer.jsp" />
