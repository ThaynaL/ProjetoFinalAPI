package org.serratec.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Autowired
    private JavaMailSender mailSender;

    public void enviar(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("#@gmail.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Confirmação de cadastro \n" + texto + "\n\n\n" + "Serratec - 2025");
        mailSender.send(message);
    }

    public void atualizar(String para, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("#.com");
        message.setTo(para);
        message.setSubject(assunto);
        message.setText("Seu cadastro foi alterado \n" + texto + "\n\n\n" + "Serratec - 2025");
        mailSender.send(message);
    }

}