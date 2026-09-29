package com.example.propertymanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Entity
@Table(name = "property")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    // FK ---> for Table ManagementOffice
    @NotNull(message = "OfficeId must be not null")
    @Positive(message = "OfficeId must be positive")
    @Column(nullable = false)
    private Long officeId;

    // FK ---> for Table Owner
    @NotNull(message = "OwnerId must be not null")
    @Positive(message = "OwnerId must be positive")
    @Column(nullable = false)
    private Long ownerId;

    @NotEmpty(message = "Name must be not empty")
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "Property Type must be not empty")
    @Pattern(regexp = "VILLA|APARTMENT|BUILDING|OFFICE|SHOP",
            message = "Property Type must be VILLA, APARTMENT, BUILDING, OFFICE, or SHOP")
    @Column(nullable = false, length = 30)
    private String propertyType;

    @NotEmpty(message = "City must be not empty")
    @Size(max = 50, message = "City must be at most 50 characters")
    @Column(nullable = false, length = 50)
    private String city;

    @NotEmpty(message = "Address must be not empty")
    @Size(max = 255, message = "Address must be at most 255 characters")
    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}