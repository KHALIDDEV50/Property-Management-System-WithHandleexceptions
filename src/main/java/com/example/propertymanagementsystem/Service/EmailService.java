package com.example.propertymanagementsystem.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;


    // Send HTML email
    public void sendEmail(
            String to,
            String subject,
            String htmlMessage) throws MessagingException {

        // Create email message
        MimeMessage email = javaMailSender.createMimeMessage();


        // Create helper for HTML email
        MimeMessageHelper helper =
                new MimeMessageHelper(
                        email,
                        true,
                        "UTF-8"
                );


        // Set receiver
        helper.setTo(to);


        // Set email subject
        helper.setSubject(subject);


        // Set HTML content
        // true = HTML
        helper.setText(
                htmlMessage,
                true
        );


        // Send email
        javaMailSender.send(email);
    }
}