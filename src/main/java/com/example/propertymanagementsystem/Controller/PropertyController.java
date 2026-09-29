package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Property;
import com.example.propertymanagementsystem.Service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;


    // Get All Properties
    @GetMapping("/get")
    public ResponseEntity<?> getAllProperties() {

        return ResponseEntity.status(200).body(propertyService.getAllProperties());
    }


    // Get Property By ID
    @GetMapping("/get/{propertyId}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long propertyId) {

        Property property = propertyService.getPropertyById(propertyId);

        return ResponseEntity.status(200).body(property);
    }


    // Add Property
    @PostMapping("/add")
    public ResponseEntity<?> addProperty(@RequestBody @Valid Property property) {

        propertyService.addProperty(property);

        return ResponseEntity.status(200).body(new ApiResponse("Property Add Successful"));
    }


    // Update Property
    @PutMapping("/update/{propertyId}")
    public ResponseEntity<?> updateProperty(@PathVariable Long propertyId, @RequestBody @Valid Property property) {

        propertyService.updateProperty(propertyId, property);

        return ResponseEntity.status(200).body(new ApiResponse("Property Update Successful"));
    }


    // Delete Property
    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId) {

        propertyService.deleteProperty(propertyId);

        return ResponseEntity.status(200).body(new ApiResponse("Property Delete Successful"));
    }

    // Search properties by owner ID
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<?> searchByOwnerId(@PathVariable Long ownerId) {

        // Get properties from service
        List<Property> properties = propertyService.searchByOwnerId(ownerId);

        return ResponseEntity.status(200).body(properties);
    }

    // Search properties by city
    @GetMapping("/city/{city}")
    public ResponseEntity<?> searchByCity(@PathVariable String city) {

        // Get properties from service
        List<Property> properties = propertyService.searchByCity(city);

        return ResponseEntity.status(200).body(properties);
    }

    //..
    // Transfer property ownership
    @PutMapping("/transfer-owner/{propertyId}/{newOwnerId}")
    public ResponseEntity<?> transferPropertyOwnership(@PathVariable Long propertyId, @PathVariable Long newOwnerId) {

        // Transfer property to new owner
        boolean isTransferred = propertyService.transferPropertyOwnership(propertyId, newOwnerId);

        return ResponseEntity.status(200).body(new ApiResponse("Property ownership transferred successfully"));
    }
}