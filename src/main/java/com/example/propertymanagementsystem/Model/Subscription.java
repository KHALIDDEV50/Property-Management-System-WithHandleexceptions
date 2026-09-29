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
@Table(name = "subscription")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    // FK ---> for Table ManagementOffice
    @NotNull(message = "OfficeId must be not null")
    @Positive(message = "OfficeId must be positive")
    @Column(nullable = false)
    private Long officeId;

    @NotEmpty(message = "Plan must be not empty")
    @Pattern(regexp = "BASIC|STANDARD|PREMIUM",
            message = "Plan must be BASIC, STANDARD, or PREMIUM"
    )
    @Column(nullable = false, length = 30)
    private String plan;

    @NotNull(message = "Start Date must be not null")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End Date must be not null")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Price must be not null")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;

    @NotEmpty(message = "Status must be not empty")
    @Pattern(regexp = "ACTIVE|EXPIRED|CANCELLED",
            message = "Status must be ACTIVE, EXPIRED, or CANCELLED"
    )
    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}