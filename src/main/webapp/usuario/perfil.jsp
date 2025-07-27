<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../WEB-INF/header.jsp">
    <jsp:param name="title" value="Perfil Usuario"/>
</jsp:include>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3">
            <jsp:include page="sidebar.jsp"/>
        </div>

        <!-- Contenido principal -->
        <div class="col-md-9">
            <h2>Mi Perfil</h2>
            <div class="card">
                <div class="card-body">
                    <p><strong>Nombre:</strong> <span id="nombreActual">${sessionScope.usuario.nombre}</span></p>
                    <p><strong>Apellido:</strong> <span id="apellidoActual">${sessionScope.usuario.apellido}</span></p>
                    <p><strong>Correo:</strong> <span id="correoActual">${sessionScope.usuario.email}</span></p>
                    <p><strong>Teléfono:</strong> <span id="telefonoActual">${sessionScope.usuario.telefono}</span></p>
                    <p><strong>Género:</strong> <span id="generoActual">${sessionScope.usuario.genero}</span></p>
                </div>
            </div>
            <button class="btn btn-warning mt-3" data-bs-toggle="modal" data-bs-target="#editarPerfilModal">
                Editar Perfil
            </button>
        </div>
    </div>
</div>

<!-- Modal de edición -->
<div class="modal fade" id="editarPerfilModal" tabindex="-1" aria-labelledby="editarPerfilLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form id="formEditarPerfil">
                <div class="modal-header">
                    <h5 class="modal-title" id="editarPerfilLabel">Editar Perfil</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <div id="mensajePerfil" class="mb-3"></div>

                    <div class="mb-3">
                        <label class="form-label">Nombre</label>
                        <input type="text" name="nombre" class="form-control" value="${sessionScope.usuario.nombre}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Apellido</label>
                        <input type="text" name="apellido" class="form-control" value="${sessionScope.usuario.apellido}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Correo</label>
                        <input type="email" name="email" class="form-control" value="${sessionScope.usuario.email}" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <input type="text" name="telefono" class="form-control" value="${sessionScope.usuario.telefono}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Género</label>
                        <select name="genero" class="form-select" required>
                            <option value="">Seleccione</option>
                            <option value="Masculino" ${sessionScope.usuario.genero == 'Masculino' ? 'selected' : ''}>Masculino</option>
                            <option value="Femenino" ${sessionScope.usuario.genero == 'Femenino' ? 'selected' : ''}>Femenino</option>
                            <option value="Otro" ${sessionScope.usuario.genero == 'Otro' ? 'selected' : ''}>Otro</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Nueva Contraseña (opcional)</label>
                        <input type="password" name="password" class="form-control" placeholder="Dejar vacío si no desea cambiar">
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- JS para enviar datos del formulario -->
<script>
document.getElementById("formEditarPerfil").addEventListener("submit", function(e) {
    e.preventDefault();

    const form = e.target;
    const formData = new FormData(form);

    fetch("actualizarPerfil", {
        method: "POST",
        body: formData
    })
    .then(res => res.json())
    .then(data => {
        const mensaje = document.getElementById("mensajePerfil");
        mensaje.innerText = data.mensaje;
        mensaje.className = data.exito ? "alert alert-success" : "alert alert-danger";

        if (data.exito) {
            // Actualiza los datos mostrados
            document.getElementById("nombreActual").innerText = form.nombre.value;
            document.getElementById("apellidoActual").innerText = form.apellido.value;
            document.getElementById("correoActual").innerText = form.email.value;
            document.getElementById("telefonoActual").innerText = form.telefono.value;
            document.getElementById("generoActual").innerText = form.genero.value;

            // Cierra el modal luego de 1.5 segundos
            setTimeout(() => {
                bootstrap.Modal.getInstance(document.getElementById('editarPerfilModal')).hide();
                mensaje.innerText = "";
            }, 1500);
        }
    })
    .catch(err => {
        console.error("Error:", err);
        const mensaje = document.getElementById("mensajePerfil");
        mensaje.innerText = "Error inesperado.";
        mensaje.className = "alert alert-danger";
    });
});
</script>

<jsp:include page="../WEB-INF/footer.jsp"/>
