package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Unit;
import com.example.propertymanagementsystem.Repository.UnitRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    // Get All Units
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    // Get Unit By ID
    public Unit getUnitById(Long unitId) {

        Unit unit = unitRepository.findById(unitId).orElse(null);

        if (unit == null) {

            throw new ApiException("Unit not found");
        }

        return unit;
    }
    // Add Unit
    public Unit addUnit(Unit unit) {

        return unitRepository.save(unit);
    }

    // Update Unit
    public Unit updateUnit(Long unitId, Unit unit) {

        Unit oldUnit =
                unitRepository.findById(unitId).orElse(null);

        if (oldUnit == null) {

            throw new ApiException("Unit not found");
        }

        oldUnit.setPropertyId(unit.getPropertyId());
        oldUnit.setUnitNumber(unit.getUnitNumber());
        oldUnit.setFloorNumber(unit.getFloorNumber());
        oldUnit.setUnitType(unit.getUnitType());
        oldUnit.setArea(unit.getArea());
        oldUnit.setBedrooms(unit.getBedrooms());
        oldUnit.setBathrooms(unit.getBathrooms());
        oldUnit.setStatus(unit.getStatus());
        oldUnit.setDescription(unit.getDescription());

        return unitRepository.save(oldUnit);
    }

    // Delete Unit
    public boolean deleteUnit(Long unitId) {

        Unit oldUnit =
                unitRepository.findById(unitId).orElse(null);

        if (oldUnit == null) {

            throw new ApiException("Unit not found");
        }

        unitRepository.delete(oldUnit);

        return true;
    }

    // Search units by property ID
    public List<Unit> searchByPropertyId(Long propertyId) {

        // Call repository query
        return unitRepository.findByPropertyId(propertyId);
    }

    // Search units by status
    public List<Unit> searchByStatus(String status) {

        // Call repository query
        return unitRepository.findByStatus(status);
    }

    // Get all available units
    public List<Unit> getAvailableUnits() {

        // Call repository query
        return unitRepository.getAvailableUnits();
    }

    // Get all occupied units
    public List<Unit> getOccupiedUnits() {

        // Call repository query
        return unitRepository.getOccupiedUnits();
    }

    // Get available units by type
    public List<Unit> getAvailableUnitsByType(String unitType) {

        // Call repository query
        return unitRepository.getAvailableUnitsByType(unitType);
    }



    // Change unit status
    public boolean changeUnitStatus(Long unitId, String status) {

        Unit unit =
                unitRepository.findById(unitId).orElse(null);

        if (unit == null) {

            throw new ApiException("Unit not found");
        }

        unit.setStatus(status);

        unitRepository.save(unit);

        return true;
    }
}
