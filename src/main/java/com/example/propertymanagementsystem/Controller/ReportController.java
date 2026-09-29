package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;


    // Get PMS report data
    @GetMapping
    public ResponseEntity<?> getReport() {

        // Get report data
        String data = reportService.getReportData();

        // Return report data
        return ResponseEntity.status(200)
                .body(data);
    }


    // Generate AI HTML report
    @GetMapping("/ai")
    public ResponseEntity<?> generateAIReport() {

        // Generate AI report
        String report = reportService.generateAIReport();

        // Return AI report
        return ResponseEntity.status(200).body(report);
    }


    // Generate AI report and send it by email
    @PostMapping("/ai/send-email")
    public ResponseEntity<?> sendAIReportByEmail(@RequestParam String email) {
        try {
            // Generate and send report
            reportService.generateAndSendAIReport(email);

            // Return success response
            return ResponseEntity.status(200).body("AI HTML report sent successfully");

        } catch (Exception e) {

            // Return error message
            return ResponseEntity.status(500).body("Failed to send AI report: " + e.getMessage());
        }
    }
}