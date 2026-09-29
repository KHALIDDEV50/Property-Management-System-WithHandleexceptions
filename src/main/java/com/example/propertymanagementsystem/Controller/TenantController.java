package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Tenant;
import com.example.propertymanagementsystem.Service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;


    // Get All Tenants
    @GetMapping("/get")
    public ResponseEntity<?> getAllTenants() {

        return ResponseEntity.status(200).body(tenantService.getAllTenants());
    }


    // Get Tenant By ID
    @GetMapping("/get/{tenantId}")
    public ResponseEntity<?> getTenantById(@PathVariable Long tenantId) {

        Tenant tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.status(200).body(tenant);
    }


    // Add Tenant
    @PostMapping("/add")
    public ResponseEntity<?> addTenant(@RequestBody @Valid Tenant tenant) {

        tenantService.addTenant(tenant);

        return ResponseEntity.status(200).body(new ApiResponse("Tenant Add Successful"));
    }


    // Update Tenant
    @PutMapping("/update/{tenantId}")
    public ResponseEntity<?> updateTenant(@PathVariable Long tenantId, @RequestBody @Valid Tenant tenant) {

        tenantService.updateTenant(tenantId, tenant);

        return ResponseEntity.status(200).body(new ApiResponse("Tenant Update Successful"));
    }


    // Delete Tenant
    @DeleteMapping("/delete/{tenantId}")
    public ResponseEntity<?> deleteTenant(@PathVariable Long tenantId) {

        tenantService.deleteTenant(tenantId);

        return ResponseEntity.status(200).body(new ApiResponse("Tenant Delete Successful"));
    }

    // Search tenants by city
    @GetMapping("/city/{city}")
    public ResponseEntity<?> searchByCity(@PathVariable String city) {

        // Get tenants from service
        List<Tenant> tenants = tenantService.searchByCity(city);

        return ResponseEntity.status(200).body(tenants);
    }

    // Search tenants by tenant type
    @GetMapping("/type/{tenantType}")
    public ResponseEntity<?> searchByTenantType(@PathVariable String tenantType) {

        // Get tenants from service
        List<Tenant> tenants = tenantService.searchByTenantType(tenantType);

        return ResponseEntity.status(200).body(tenants);
    }
}