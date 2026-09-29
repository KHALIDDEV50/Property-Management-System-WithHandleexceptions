package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Owner;
import com.example.propertymanagementsystem.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;


    // Get All Owners
    @GetMapping("/get")
    public ResponseEntity<?> getAllOwners() {

        return ResponseEntity.status(200).body(ownerService.getAllOwners());
    }

    // Get Owner By ID
    @GetMapping("/get/{ownerId}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long ownerId) {

        Owner owner = ownerService.getOwnerById(ownerId);
        return ResponseEntity.status(200).body(owner);
    }


    // Add Owner
    @PostMapping("/add")
    public ResponseEntity<?> addOwner(@RequestBody @Valid Owner owner) {

        ownerService.addOwner(owner);
        return ResponseEntity.status(200).body(new ApiResponse("Owner Add Successful"));
    }


    // Update Owner
    @PutMapping("/update/{ownerId}")
    public ResponseEntity<?> updateOwner(@PathVariable Long ownerId, @RequestBody @Valid Owner owner, Errors errors) {

        if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        Owner oldOwner = ownerService.getOwnerById(ownerId);

        if (oldOwner == null) {

            return ResponseEntity.status(404).body(new ApiResponse("Owner not found"));
        }

        ownerService.updateOwner(ownerId, owner);

        return ResponseEntity.status(200).body(new ApiResponse("Owner Update Successful"));
    }


    // Delete Owner
    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<?> deleteOwner(@PathVariable Long ownerId) {

        ownerService.deleteOwner(ownerId);

        return ResponseEntity.status(200).body(new ApiResponse("Owner Delete Successful"));
    }

    // Search owners by office ID
    @GetMapping("/office/{officeId}")
    public ResponseEntity<?> searchByOfficeId(@PathVariable Long officeId) {

        // Get owners from service
        List<Owner> owners = ownerService.searchByOfficeId(officeId);

        return ResponseEntity.status(200).body(owners);
    }

    // Search owners by owner type
    @GetMapping("/type/{ownerType}")
    public ResponseEntity<?> searchByOwnerType(@PathVariable String ownerType) {

        // Get owners from service
        List<Owner> owners = ownerService.searchByOwnerType(ownerType);

        return ResponseEntity.status(200).body(owners);
    }
}