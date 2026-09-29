package com.example.propertymanagementsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Entity
@Table(name = "owner")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    // FK ---> for Table ManagementOffice
    @NotNull(message = "OfficeId must be not null")
    @Positive(message = "OfficeId must be positive")
    @Column(nullable = false)
    private Long officeId;

    @NotEmpty(message = "Name must be not empty")
    @Size(max = 100, message = "Name must be at most 100 characters")
    @Column(nullable = false,length = 100)
    private String name;

    @NotEmpty(message = "Owner Type must be not empty")
    @Pattern(regexp = "INDIVIDUAL|COMPANY", message = "Owner Type must be INDIVIDUAL or COMPANY")
    @Column(nullable = false,length = 15)
    private String ownerType;

    @NotEmpty(message = "Identification Number must be not empty")
    @Size(max = 10, message = "Identification Number must be at most 10 characters")
    @Column(nullable = false,unique = true,length = 10)
    private String identificationNumber;


    @NotEmpty(message = "Phone must be not empty")
    @Size(max = 10,message = "Phone must be at most 10 characters")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone must be a valid Saudi mobile number")
    @Column(nullable = false,length = 10)
    private String phone;

    @NotEmpty(message = "Email must be not empty")
    @Email(message = "Invalid Email")
    @Column(nullable = false,length = 100)
    private String email;

    @NotEmpty(message = "Address must be not empty")
    @Size(max = 255, message = "Address must be at most 255 characters")
    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
