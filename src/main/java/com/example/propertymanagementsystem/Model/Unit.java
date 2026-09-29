package com.example.propertymanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Entity
@Table(name = "unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    // FK ---> for Table Property
    @NotNull(message = "PropertyId must be not null")
    @Positive(message = "PropertyId must be positive")
    @Column(nullable = false)
    private Long propertyId;

    @NotEmpty(message = "Unit Number must be not empty")
    @Size(max = 30, message = "Unit Number must be at most 30 characters")
    @Column(nullable = false, length = 30)
    private String unitNumber;

    @NotNull(message = "Floor Number must be not null")
    @Min(value = 0, message = "Floor Number must be 0 or greater")
    @Column(nullable = false)
    private Integer floorNumber;

    @NotEmpty(message = "Unit Type must be not empty")
    @Pattern(
            regexp = "APARTMENT|OFFICE|SHOP|STUDIO",
            message = "Unit Type must be APARTMENT, OFFICE, SHOP, or STUDIO"
    )
    @Column(nullable = false, length = 30)
    private String unitType;

    @NotNull(message = "Area must be not null")
    @Positive(message = "Area must be positive")
    @Column(nullable = false)
    private Double area;

    @NotNull(message = "Bedrooms must be not null")
    @Min(value = 0, message = "Bedrooms must be 0 or greater")
    @Column(nullable = false)
    private Integer bedrooms;

    @NotNull(message = "Bathrooms must be not null")
    @Min(value = 0, message = "Bathrooms must be 0 or greater")
    @Column(nullable = false)
    private Integer bathrooms;

    @NotEmpty(message = "Status must be not empty")
    @Pattern(
            regexp = "AVAILABLE|OCCUPIED|MAINTENANCE",
            message = "Status must be AVAILABLE, OCCUPIED, or MAINTENANCE"
    )
    @Column(nullable = false, length = 30)
    private String status;

    @Size(max = 500, message = "Description must be at most 500 characters")
    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}