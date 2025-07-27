package com.mycompany.patinetastienda.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

import java.util.Properties;

public class EmailUtil {

    public static void enviarCodigoVerificacion(String destinatario, String codigo) throws MessagingException, UnsupportedEncodingException {
        // Usuario SMTP proporcionado por Brevo
        final String usuarioSMTP = "91ff0e003@smtp-brevo.com";
        final String claveSMTP = "1KpDf3mjXvERY2IV"; // tu clave SMTP generada

        // Remitente real verificado en Brevo
        final String correoRemitente = "tablabravatienda@gmail.com";
        final String nombreRemitente = "Tabla Brava";

        // Configuración SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-relay.brevo.com");
        props.put("mail.smtp.port", "587");

        // Sesión de correo con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuarioSMTP, claveSMTP);
            }
        });

        // Crear el mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(correoRemitente, nombreRemitente)); // Remitente visible
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject("Código de verificación - Tabla Brava");
        message.setText("Hola,\n\nTu código de verificación es: " + codigo + "\n\nGracias por registrarte en Tabla Brava.\n");

        // Enviar
        Transport.send(message);
    }
}
