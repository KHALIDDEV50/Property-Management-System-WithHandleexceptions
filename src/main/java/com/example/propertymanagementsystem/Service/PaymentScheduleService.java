package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Model.PaymentSchedule;
import com.example.propertymanagementsystem.Repository.ContractRepository;
import com.example.propertymanagementsystem.Repository.PaymentScheduleRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;
    private final ContractRepository contractRepository;
    private final EmailService emailService;

    // Get All Payment Schedules
    public List<PaymentSchedule> getAllPaymentSchedules() {
        return paymentScheduleRepository.findAll();
    }


    // Get Payment Schedule By ID
    public PaymentSchedule getPaymentScheduleById(Long paymentScheduleId) {

        PaymentSchedule paymentSchedule = paymentScheduleRepository.findById(paymentScheduleId).orElse(null);

        if (paymentSchedule == null) {

            throw new ApiException("Payment Schedule not found");
        }

        return paymentSchedule;
    }

    // Add Payment Schedule
    public PaymentSchedule addPaymentSchedule(PaymentSchedule paymentSchedule) {
        return paymentScheduleRepository.save(paymentSchedule);
    }

    // Update Payment Schedule
    public PaymentSchedule updatePaymentSchedule(Long paymentScheduleId, PaymentSchedule paymentSchedule) {

        PaymentSchedule oldPaymentSchedule = paymentScheduleRepository.findById(paymentScheduleId).orElse(null);

        if (oldPaymentSchedule == null) {

            throw new ApiException("Payment Schedule not found");
        }

        oldPaymentSchedule.setContractId(paymentSchedule.getContractId());

        oldPaymentSchedule.setInstallmentNumber(paymentSchedule.getInstallmentNumber());

        oldPaymentSchedule.setDueDate(paymentSchedule.getDueDate());

        oldPaymentSchedule.setAmount(paymentSchedule.getAmount());

        oldPaymentSchedule.setStatus(paymentSchedule.getStatus());

        oldPaymentSchedule.setPaidDate(paymentSchedule.getPaidDate());

        oldPaymentSchedule.setNotes(paymentSchedule.getNotes());

        return paymentScheduleRepository.save(oldPaymentSchedule);
    }


    // Delete Payment Schedule
    public boolean deletePaymentSchedule(Long paymentScheduleId) {

        PaymentSchedule oldPaymentSchedule = paymentScheduleRepository.findById(paymentScheduleId).orElse(null);

        if (oldPaymentSchedule == null) {
            throw new ApiException("Payment Schedule not found");
        }

        paymentScheduleRepository.delete(oldPaymentSchedule);

        return true;
    }

    // Search payment schedules by contract ID
    public List<PaymentSchedule> searchByContractId(Long contractId) {

        // Call repository query
        return paymentScheduleRepository.findByContractId(contractId);
    }

    // Search payment schedules by status
    public List<PaymentSchedule> searchByStatus(String status) {

        // Call repository query
        return paymentScheduleRepository.findByStatus(status);
    }

    // Search payment schedules by due date
    public List<PaymentSchedule> searchByDueDate(LocalDate dueDate) {

        // Call repository query
        return paymentScheduleRepository.findByDueDate(dueDate);
    }

    // Update payment status and send Email
    public boolean updatePaymentStatus(Long paymentScheduleId, String status) {

        // Get payment schedule by ID
        PaymentSchedule paymentSchedule = paymentScheduleRepository.findById(paymentScheduleId).orElse(null);

        // Check if payment schedule exists
        if (paymentSchedule == null) {

            throw new ApiException("Payment Schedule not found");
        }

        // Change payment status
        paymentSchedule.setStatus(status);

        // If payment is paid
        if (status.equals("PAID")) {

            // Set paid date
            paymentSchedule.setPaidDate(LocalDate.now()
            );

            // Get contract
            Contract contract = contractRepository.findById(paymentSchedule.getContractId()).orElse(null);

            // Check if contract exists
            if (contract == null) {

                throw new ApiException("Contract not found");
            }

            try {

                // Send payment confirmation email
                emailService.sendEmail(
                        "cs.develop71@gmail.com",
                        "Payment Received",
                        "Payment has been received successfully."
                );

            } catch (MessagingException e) {

                // Email error
                System.out.println("Failed to send payment email: " + e.getMessage()
                );
            }
        }

        // Save updated payment schedule
        paymentScheduleRepository.save(
                paymentSchedule
        );

        return true;
    }


    // Generate next payment
    public boolean generateNextPayment(Long contractId) {

        // Get the contract
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Get previous payments for this contract
        List<PaymentSchedule> payments = paymentScheduleRepository.getLatestPayment(contractId);

        // Get contract payment frequency
        String frequency = contract.getPaymentFrequency();

        // Create new payment schedule
        PaymentSchedule payment = new PaymentSchedule();

        // Set contract ID
        payment.setContractId(contractId);

        // Set payment amount
        payment.setAmount(contract.getRentAmount());

        // Set payment status
        payment.setStatus("PENDING");

        // Set paid date as null
        payment.setPaidDate(null);

        // Set created date
        payment.setCreatedAt(LocalDateTime.now());

        // Calculate installment number and due date
        if (payments.isEmpty()) {

            // First payment
            payment.setInstallmentNumber(1);

            // First payment due date
            payment.setDueDate(contract.getStartDate()
            );

        } else {

            // Get latest payment
            PaymentSchedule latestPayment = payments.get(0);

            // Set next installment number
            payment.setInstallmentNumber(latestPayment.getInstallmentNumber() + 1
            );

            // Get latest payment due date
            LocalDate nextDate = latestPayment.getDueDate();

            // Calculate next due date
            if (frequency.equals("MONTHLY")) {

                nextDate = nextDate.plusMonths(1);

            } else if (frequency.equals("QUARTERLY")) {

                nextDate = nextDate.plusMonths(3);

            } else if (frequency.equals("SEMI_ANNUAL")) {

                nextDate = nextDate.plusMonths(6);

            } else if (frequency.equals("ANNUAL")) {

                nextDate = nextDate.plusYears(1);
            }

            // Set next due date
            payment.setDueDate(nextDate);
        }

        // Save the new payment
        paymentScheduleRepository.save(payment);

        return true;
    }
}
