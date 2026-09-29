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
@Table(name = "contract")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    // FK ---> for Table Unit
    @NotNull(message = "UnitId must be not null")
    @Positive(message = "UnitId must be positive")
    @Column(nullable = false)
    private Long unitId;

    // FK ---> for Table Tenant
    @NotNull(message = "TenantId must be not null")
    @Positive(message = "TenantId must be positive")
    @Column(nullable = false)
    private Long tenantId;

    @NotEmpty(message = "Contract Number must be not empty")
    @Size(max = 50, message = "Contract Number must be at most 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String contractNumber;

    @NotNull(message = "Start Date must be not null")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End Date must be not null")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Rent Amount must be not null")
    @Positive(message = "Rent Amount must be positive")
    @Column(nullable = false)
    private Double rentAmount;

    @NotEmpty(message = "Payment Frequency must be not empty")
    @Pattern(
            regexp = "MONTHLY|QUARTERLY|SEMI_ANNUAL|ANNUAL",
            message = "Payment Frequency must be MONTHLY, QUARTERLY, SEMI_ANNUAL, or ANNUAL"
    )
    @Column(nullable = false, length = 30)
    private String paymentFrequency;

    @NotEmpty(message = "Status must be not empty")
    @Pattern(
            regexp = "ACTIVE|EXPIRED|CANCELLED",
            message = "Status must be ACTIVE, EXPIRED, or CANCELLED"
    )
    @Column(nullable = false, length = 30)
    private String status;

    @Size(max = 500, message = "Notes must be at most 500 characters")
    @Column(length = 500)
    private String notes;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}