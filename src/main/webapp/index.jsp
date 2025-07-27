<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="WEB-INF/header.jsp">
    <jsp:param name="title" value="Inicio"/>
</jsp:include>

<!-- Hero Section -->
<section class="hero-section d-flex align-items-stretch">
    <div class="container-fluid">
        <div class="row g-0 align-items-stretch min-vh-100">
            <div class="col-lg-6 d-flex align-items-center px-5">
                <div class="hero-content">
                    <h1 class="hero-title">Bienvenido a Tabla Brava</h1>
                    <p class="hero-subtitle">Tu tienda de confianza para todo lo relacionado con el skateboarding</p>
                    <p class="hero-description">
                        Desde 2024, Ofreciendo las mejores marcas, equipos de calidad y un servicio personalizado para que 
                        puedas vivir tu pasión al máximo. Ya seas principiante o profesional, aquí encontrarás 
                        todo lo que necesitas para dominar las calles.
                    </p>
                    <div class="hero-buttons">
                        <a href="skates.jsp" class="btn btn-primary btn-lg">Ver Productos</a>
                        <a href="contacto.jsp" class="btn btn-outline-primary btn-lg">Contáctanos</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 hero-image p-0">
                <img src="assets/img/Skate.jpg" class="img-fluid w-100 h-100 object-fit-cover" alt="Skater realizando truco">
            </div>
        </div>
    </div>
</section>

<!-- Categorías Section -->
<section class="categorias-section">
    <div class="container">
        <h2 class="section-title">Nuestras Categorías</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="categoria-card">
                    <img src="assets/img/345.jpg" class="categoria-img" alt="Skateboards">
                    <div class="categoria-content">
                        <h3>Skateboards Completos</h3>
                        <p>Tablas armadas listas para rodar, perfectas para principiantes y expertos.</p>
                        <a href="productos.jsp?categoria=completos" class="btn btn-outline-primary">Ver Más</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="categoria-card">
                    <img src="assets/img/3567.jpg" class="categoria-img" alt="Decks">
                    <div class="categoria-content">
                        <h3>Decks & Tablas</h3>
                        <p>Tablas de las mejores marcas en diferentes tamaños y diseños únicos.</p>
                        <a href="productos.jsp?categoria=decks" class="btn btn-outline-primary">Ver Más</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="categoria-card">
                    <img src="assets/img/images.jpeg" class="categoria-img" alt="Accesorios">
                    <div class="categoria-content">
                        <h3>Accesorios</h3>
                        <p>Trucks, ruedas, rodamientos, lijas y todo lo que necesitas para tu setup.</p>
                        <a href="productos.jsp?categoria=accesorios" class="btn btn-outline-primary">Ver Más</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Marcas Section -->
<section class="marcas-section">
    <div class="container text-center">
        <h2 class="section-title mb-4">Colaboradores</h2>
        <div class="row justify-content-center">
            <div class="col-md-2 col-6 mb-3 animate-fade">
                <p class="marca-nombre">Sketchers</p>
            </div>
            <div class="col-md-2 col-6 mb-3 animate-fade">
                <p class="marca-nombre">Xtreme</p>
            </div>
            <div class="col-md-2 col-6 mb-3 animate-fade">
                <p class="marca-nombre">Tabla Brava</p>
            </div>
            <div class="col-md-2 col-6 mb-3 animate-fade">
                <p class="marca-nombre">Nike</p>
            </div>
            <div class="col-md-2 col-6 mb-3 animate-fade">
                <p class="marca-nombre">Skate Mental</p>
            </div>
        </div>
    </div>
</section>

<style>
    .marca-nombre {
        font-size: 1.2rem;
        font-weight: bold;
        color: #333;
        background-color: #f5f5f5;
        padding: 12px;
        border-radius: 12px;
        transition: transform 0.3s ease, background-color 0.3s ease;
    }

    .marca-nombre:hover {
        transform: scale(1.05);
        background-color: #e0e0e0;
        cursor: pointer;
    }

    /* Animación fade-in */
    .animate-fade {
        opacity: 0;
        transform: translateY(20px);
        animation: fadeInUp 0.8s ease forwards;
    }

    .animate-fade:nth-child(1) { animation-delay: 0.1s; }
    .animate-fade:nth-child(2) { animation-delay: 0.2s; }
    .animate-fade:nth-child(3) { animation-delay: 0.3s; }
    .animate-fade:nth-child(4) { animation-delay: 0.4s; }
    .animate-fade:nth-child(5) { animation-delay: 0.5s; }

    @keyframes fadeInUp {
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>

<!-- Info Section -->
<section class="info-section">
    <div class="container">
        <div class="row g-4">
            <div class="col-md-6">
                <div class="info-card">
                    <div class="info-icon">
                        <i class="fas fa-shipping-fast"></i>
                    </div>
                    <div class="info-content">
                        <h3>Envío Gratis</h3>
                        <p>En compras mayores a S/. 150 dentro de Lima. Envíos a todo el Perú.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="info-card">
                    <div class="info-icon">
                        <i class="fas fa-tools"></i>
                    </div>
                    <div class="info-content">
                        <h3>Armado Gratis</h3>
                        <p>Armamos tu skateboard sin costo adicional con todas las compras.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="info-card">
                    <div class="info-icon">
                        <i class="fas fa-medal"></i>
                    </div>
                    <div class="info-content">
                        <h3>Garantía de Calidad</h3>
                        <p>Todos nuestros productos cuentan con garantía del fabricante.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="info-card">
                    <div class="info-icon">
                        <i class="fas fa-users"></i>
                    </div>
                    <div class="info-content">
                        <h3>Comunidad Skater</h3>
                        <p>Únete a nuestra comunidad y participa en eventos y competencias.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Tips Section -->
<section class="tips-section">
    <div class="container">
        <h2 class="section-title">Tips para Skaters</h2>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="tip-card">
                    <img src="assets/img/wwa.jpg" class="tip-img" alt="Principiante">
                    <div class="tip-content">
                        <h4>Para Principiantes</h4>
                        <p>Comienza con una tabla ancha (8.0" o más) para mayor estabilidad. 
                           Practica primero el equilibrio antes de intentar trucos.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="tip-card">
                    <img src="assets/img/hq720.jpg" class="tip-img" alt="Mantenimiento">
                    <div class="tip-content">
                        <h4>Mantenimiento</h4>
                        <p>Limpia tus rodamientos regularmente y revisa que los trucks estén 
                           bien ajustados para un mejor rendimiento.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="tip-card">
                    <img src="assets/img/asff.jpg" class="tip-img" alt="Seguridad">
                    <div class="tip-content">
                        <h4>Seguridad</h4>
                        <p>Siempre usa casco y protecciones. La seguridad es lo más importante 
                           para disfrutar del skateboarding.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="WEB-INF/footer.jsp"/>
