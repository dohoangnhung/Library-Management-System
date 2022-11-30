package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String recipient, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("sankayou.diephason@gmail.com");
        mail.setTo(recipient);
        mail.setSubject(subject);
        mail.setText(body);

        mailSender.send(mail);
    }
}
