package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.PaymentSchedule;
import com.example.propertymanagementsystem.Service.PaymentScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-schedule")
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService;


    // Get All Payment Schedules
    @GetMapping("/get")
    public ResponseEntity<?> getAllPaymentSchedules() {

        return ResponseEntity.status(200).body(paymentScheduleService.getAllPaymentSchedules());
    }


    // Get Payment Schedule By ID
    @GetMapping("/get/{paymentScheduleId}")
    public ResponseEntity<?> getPaymentScheduleById(@PathVariable Long paymentScheduleId) {

        PaymentSchedule paymentSchedule = paymentScheduleService.getPaymentScheduleById(paymentScheduleId);

        return ResponseEntity.status(200).body(paymentSchedule);
    }


    // Add Payment Schedule
    @PostMapping("/add")
    public ResponseEntity<?> addPaymentSchedule(@RequestBody @Valid PaymentSchedule paymentSchedule) {

        paymentScheduleService.addPaymentSchedule(paymentSchedule);

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Add Successful"));
    }


    // Update Payment Schedule
    @PutMapping("/update/{paymentScheduleId}")
    public ResponseEntity<?> updatePaymentSchedule(@PathVariable Long paymentScheduleId, @RequestBody @Valid PaymentSchedule paymentSchedule) {


        paymentScheduleService.updatePaymentSchedule(paymentScheduleId, paymentSchedule);

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Update Successful"));
    }


    // Delete Payment Schedule
    @DeleteMapping("/delete/{paymentScheduleId}")
    public ResponseEntity<?> deletePaymentSchedule(@PathVariable Long paymentScheduleId) {



        paymentScheduleService.deletePaymentSchedule(paymentScheduleId);

        return ResponseEntity.status(200).body(new ApiResponse("Payment Schedule Delete Successful"));
    }

    // Search payment schedules by contract ID
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<?> searchByContractId(@PathVariable Long contractId) {

        // Get payment schedules from service
        List<PaymentSchedule> paymentSchedules = paymentScheduleService.searchByContractId(contractId);

        return ResponseEntity.status(200).body(paymentSchedules);
    }

    // Search payment schedules by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable String status) {

        // Get payment schedules from service
        List<PaymentSchedule> paymentSchedules =
                paymentScheduleService.searchByStatus(status);

        return ResponseEntity.status(200).body(paymentSchedules);
    }

    // Search payment schedules by due date
    @GetMapping("/due-date/{dueDate}")
    public ResponseEntity<?> searchByDueDate(@PathVariable LocalDate dueDate) {

        // Get payment schedules from service
        List<PaymentSchedule> paymentSchedules = paymentScheduleService.searchByDueDate(dueDate);

        return ResponseEntity.status(200).body(paymentSchedules);
    }

    //..

    // Update payment status
    @PutMapping("/status/{paymentScheduleId}/{status}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long paymentScheduleId, @PathVariable String status) {


        paymentScheduleService.updatePaymentStatus(paymentScheduleId, status);

        return ResponseEntity.status(200).body(new ApiResponse("Payment status updated successfully"));
    }

    //..
    // Generate next payment
    @PostMapping("/generate/{contractId}")
    public ResponseEntity<?> generateNextPayment(@PathVariable Long contractId) {
        paymentScheduleService.generateNextPayment(contractId);

        return ResponseEntity.status(200).body(new ApiResponse("Payment schedule generated successfully"));
    }
}