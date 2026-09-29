package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.Service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    // Send email
    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message) {

        try {

            // Send email
            emailService.sendEmail(to, subject, message);

            return ResponseEntity.status(200)
                    .body("Email sent successfully");

        } catch (MessagingException e) {

            return ResponseEntity.status(500)
                    .body("Failed to send email: " + e.getMessage());
        }
    }
}