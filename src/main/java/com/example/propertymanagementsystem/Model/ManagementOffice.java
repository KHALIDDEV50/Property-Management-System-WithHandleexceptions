package com.example.propertymanagementsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Entity
@Table(name = "management_office")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ManagementOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(message = "Office ID must be positive")
    private Long officeId;

    @NotEmpty(message = "Name must be not empty")
    @Size(max = 100 , message = "Name must be at most 100 characters")
    @Column(nullable = false,length = 100)
    private String name;


    @NotEmpty(message = "commercial Registration must be not empty")
    @Size(max = 10, message = "Commercial Registration must be at most 10 characters")
    @Pattern(regexp = "^[0-9]{10}$", message = "Commercial Registration must contain exactly 10 digits")
    @Column(nullable = false,unique = true,length = 10)
    private String commercialRegistration;

    @NotEmpty(message = "phone must be not empty")
    @Size(max = 10, message = "phone must be less than 10 characters")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone must be a valid Saudi mobile number")
    @Column(nullable = false,length = 10)
    private String phone;

    @NotEmpty(message = "email must be not empty")
    @Email(message = "Invalid Email")
    @Column(nullable = false,unique = true,length = 100)
    private String email;

    @NotEmpty(message = "city must be not empty")
    @Size(max = 20 , message = "city must be at most 20 characters")
    @Column(nullable = false,length = 20)
    private String city;

    @NotEmpty(message = "address must be not empty")
    @Size(max = 100 , message = "Address must be at most 100 characters")
    @Column(nullable = false,length = 100)
    private String address;

    @NotEmpty(message = "Status must be not empty")
    @Pattern(regexp = "ACTIVE|INACTIVE|SUSPENDED", message = "Status must be ACTIVE, INACTIVE, or SUSPENDED")
    @Column(nullable = false, length = 30)
    private String status;

    @Column(nullable = false)
   private LocalDateTime createdAt;

}
