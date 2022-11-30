package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String recipient, String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("sankayou.diephason@gmail.com");
        mail.setTo(recipient);
        mail.setSubject(subject);
        mail.setText(body);

        mailSender.send(mail);
    }

    public void sendMailWithAttachment(String recipient, String subject, String body, String attachment) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("sankayou.diephason@gmail.com");
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            FileSystemResource file = new FileSystemResource(new File(attachment));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("Error while sending email!");
        }
    }

}
