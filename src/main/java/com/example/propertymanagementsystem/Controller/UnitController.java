package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Unit;
import com.example.propertymanagementsystem.Service.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;


    // Get All Units
    @GetMapping("/get")
    public ResponseEntity<?> getAllUnits() {

        return ResponseEntity.status(200).body(unitService.getAllUnits());
    }


    // Get Unit By ID
    @GetMapping("/get/{unitId}")
    public ResponseEntity<?> getUnitById(@PathVariable Long unitId) {

        Unit unit = unitService.getUnitById(unitId);

        return ResponseEntity.status(200).body(unit);
    }


    // Add Unit
    @PostMapping("/add")
    public ResponseEntity<?> addUnit(@RequestBody @Valid Unit unit) {

        unitService.addUnit(unit);

        return ResponseEntity.status(200).body(new ApiResponse("Unit Add Successful"));
    }


    // Update Unit
    @PutMapping("/update/{unitId}")
    public ResponseEntity<?> updateUnit(@PathVariable Long unitId, @RequestBody @Valid Unit unit) {

        unitService.updateUnit(unitId, unit);

        return ResponseEntity.status(200).body(new ApiResponse("Unit Update Successful"));
    }


    // Delete Unit
    @DeleteMapping("/delete/{unitId}")
    public ResponseEntity<?> deleteUnit(@PathVariable Long unitId) {
        unitService.deleteUnit(unitId);
        return ResponseEntity.status(200).body(new ApiResponse("Unit Delete Successful"));
    }

    // Search units by property ID
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<?> searchByPropertyId(@PathVariable Long propertyId) {

        // Get units from service
        List<Unit> units = unitService.searchByPropertyId(propertyId);

        return ResponseEntity.status(200).body(units);
    }

    // Search units by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable String status) {

        // Get units from service
        List<Unit> units = unitService.searchByStatus(status);

        return ResponseEntity.status(200).body(units);
    }

    // Get all available units
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableUnits() {

        // Get available units from service
        List<Unit> units = unitService.getAvailableUnits();

        return ResponseEntity.status(200).body(units);
    }

    // Get all occupied units
    @GetMapping("/occupied")
    public ResponseEntity<?> getOccupiedUnits() {

        // Get occupied units from service
        List<Unit> units = unitService.getOccupiedUnits();

        return ResponseEntity.status(200).body(units);
    }

    // Get available units by type
    @GetMapping("/available/type/{unitType}")
    public ResponseEntity<?> getAvailableUnitsByType(@PathVariable String unitType) {

        // Get available units by type from service
        List<Unit> units = unitService.getAvailableUnitsByType(unitType);

        return ResponseEntity.status(200).body(units);
    }

    // Change unit status
    @PutMapping("/status/{unitId}/{status}")
    public ResponseEntity<?> changeUnitStatus(@PathVariable Long unitId, @PathVariable String status) {

        return ResponseEntity.status(200).body(new ApiResponse("Unit status updated successfully"));
    }
}