<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <div class="footer-container">
        <div class="footer-section">
            <h3>Tabla Brava</h3>
            <p>Tu tienda de skate virtual. Pasión por el skateboarding desde 2024.</p>
            <div class="social-links">
                <a href="https://facebook.com/tablabrava" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                </a>
                <a href="https://instagram.com/tablabrava" target="_blank">
                    <i class="fab fa-instagram"></i>
                </a>
                <a href="https://tiktok.com/@tablabrava" target="_blank">
                    <i class="fab fa-tiktok"></i>
                </a>
                <a href="https://youtube.com/tablabrava" target="_blank">
                    <i class="fab fa-youtube"></i>
                </a>
            </div>
        </div>

        <div class="footer-section">
            <h4>Contacto</h4>
            <div class="contact-info">
                <p><i class="fas fa-envelope"></i> tablabravatienda@gmail.com</p>
                <p><i class="fas fa-phone"></i> +51 987 654 321</p>
                <p><i class="fas fa-whatsapp"></i> +51 987 654 321</p>
            </div>
        </div>

        <div class="footer-section">
            <h4>Horarios</h4>
            <div class="schedule">
                <p><strong>Lunes - Viernes:</strong> 10:00 AM - 8:00 PM</p>
                <p><strong>Sábados:</strong> 10:00 AM - 9:00 PM</p>
                <p><strong>Domingos:</strong> 11:00 AM - 6:00 PM</p>
                <p><strong>Feriados:</strong> Horario especial</p>
            </div>
        </div>

        <div class="footer-section">
            <h4>Categorías</h4>
            <ul class="footer-links">
                <li><a href="skates.jsp">Skates</a></li>
                <li><a href="tablas.jsp">Tablas</a></li>
                <li><a href="ruedas.jsp">Ruedas</a></li>
                <li><a href="accesorios.jsp">Accesorios</a></li>
            </ul>
        </div>

        <div class="footer-section">
            <h4>Información</h4>
            <ul class="footer-links">
                <li><a href="nosotros.jsp">Nosotros</a></li>
                <li><a href="envios.jsp">Envíos</a></li>
                <li><a href="cambios.jsp">Cambios y Devoluciones</a></li>
                <li><a href="terminos.jsp">Términos y Condiciones</a></li>
                <li><a href="privacidad.jsp">Política de Privacidad</a></li>
                <li><a href="faq.jsp">Preguntas Frecuentes</a></li>
            </ul>
        </div>

        <div class="footer-section newsletter">
            <h4>Newsletter</h4>
            <p>Suscríbete y recibe ofertas exclusivas</p>
            <form action="newsletter.jsp" method="post" class="newsletter-form">
                <input type="email" name="email" placeholder="Tu email" required>
                <button type="submit">Suscribirse</button>
            </form>
            <div class="payment-methods">
                <h5>Métodos de Pago</h5>
                <div class="payment-icons">
                    <i class="fab fa-cc-visa"></i>
                    <i class="fab fa-cc-mastercard"></i>
                    <i class="fas fa-credit-card"></i>
                    <span>Yape</span>
                    <span>Plin</span>
                </div>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        <div class="footer-bottom-content">
            <p>&copy; <%= new java.util.Date().getYear() + 1900 %> Tabla Brava. Todos los derechos reservados.</p>
            <p>RUC: 20123456789 | Hecho en Perú con ❤️ para los skaters</p>
        </div>
    </div>
</footer>

<style>
.footer {
    background: linear-gradient(135deg, #1a1a1a, #2d2d2d);
    color: #ffffff;
    padding: 40px 0 20px;
    margin-top: 50px;
    font-family: 'Arial', sans-serif;
}

.footer-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 30px;
}

.footer-section h3 {
    color: #ff6b35;
    font-size: 24px;
    margin-bottom: 15px;
    font-weight: bold;
}

.footer-section h4 {
    color: #ff6b35;
    font-size: 18px;
    margin-bottom: 15px;
    border-bottom: 2px solid #ff6b35;
    padding-bottom: 5px;
}

.footer-section h5 {
    color: #ff6b35;
    font-size: 14px;
    margin-bottom: 10px;
}

.footer-section p {
    line-height: 1.6;
    margin-bottom: 10px;
}

.social-links {
    display: flex;
    gap: 15px;
    margin-top: 20px;
}

.social-links a {
    color: #ffffff;
    font-size: 20px;
    transition: color 0.3s, transform 0.3s;
}

.social-links a:hover {
    color: #ff6b35;
    transform: translateY(-2px);
}

.contact-info p {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-bottom: 8px;
}

.contact-info i {
    color: #ff6b35;
    width: 20px;
}

.schedule p {
    margin-bottom: 5px;
}

.footer-links {
    list-style: none;
    padding: 0;
}

.footer-links li {
    margin-bottom: 8px;
}

.footer-links a {
    color: #cccccc;
    text-decoration: none;
    transition: color 0.3s;
}

.footer-links a:hover {
    color: #ff6b35;
}

.newsletter-form {
    display: flex;
    gap: 10px;
    margin-top: 15px;
    margin-bottom: 20px;
}

.newsletter-form input {
    flex: 1;
    padding: 10px;
    border: none;
    border-radius: 5px;
    background: #444;
    color: #fff;
}

.newsletter-form button {
    padding: 10px 20px;
    background: #ff6b35;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s;
}

.newsletter-form button:hover {
    background: #e55a2e;
}

.payment-icons {
    display: flex;
    gap: 10px;
    align-items: center;
    flex-wrap: wrap;
}

.payment-icons i {
    font-size: 24px;
    color: #ff6b35;
}

.payment-icons span {
    background: #ff6b35;
    color: white;
    padding: 5px 10px;
    border-radius: 3px;
    font-size: 12px;
    font-weight: bold;
}

.footer-bottom {
    border-top: 1px solid #444;
    margin-top: 30px;
    padding-top: 20px;
    text-align: center;
}

.footer-bottom-content {
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

.footer-bottom p {
    color: #cccccc;
    margin-bottom: 5px;
}

@media (max-width: 768px) {
    .footer-container {
        grid-template-columns: 1fr;
        gap: 20px;
    }
    
    .newsletter-form {
        flex-direction: column;
    }
    
    .payment-icons {
        justify-content: center;
    }
}
</style>

<!-- Font Awesome para los iconos -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">