package com.example.propertymanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_schedule")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PaymentSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentScheduleId;

    // FK ---> for Table Contract
    @NotNull(message = "ContractId must be not null")
    @Positive(message = "ContractId must be positive")
    @Column(nullable = false)
    private Long contractId;

    @NotNull(message = "Installment Number must be not null")
    @Positive(message = "Installment Number must be positive")
    @Column(nullable = false)
    private Integer installmentNumber;

    @NotNull(message = "Due Date must be not null")
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull(message = "Amount must be not null")
    @Positive(message = "Amount must be positive")
    @Column(nullable = false)
    private Double amount;

    @NotEmpty(message = "Status must be not empty")
    @Pattern(
            regexp = "PENDING|PAID|OVERDUE|CANCELLED",
            message = "Status must be PENDING, PAID, OVERDUE, or CANCELLED"
    )
    @Column(nullable = false, length = 30)
    private String status;

    private LocalDate paidDate;

    @Size(max = 500, message = "Notes must be at most 500 characters")
    @Column(length = 500)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}