package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit,Long> {

    // Search units by property ID
    @Query("SELECT u FROM Unit u WHERE u.propertyId = ?1")
    List<Unit> findByPropertyId(Long propertyId);

    // Search units by status
    @Query("SELECT u FROM Unit u WHERE u.status = ?1")
    List<Unit> findByStatus(String status);

    // Get all available units
    @Query("SELECT u FROM Unit u WHERE u.status = 'AVAILABLE'")
    List<Unit> getAvailableUnits();

    // Get all occupied units
    @Query("SELECT u FROM Unit u WHERE u.status = 'OCCUPIED'")
    List<Unit> getOccupiedUnits();

    // Get available units by type
    @Query("SELECT u FROM Unit u WHERE u.unitType = ?1 AND u.status = 'AVAILABLE'")
    List<Unit> getAvailableUnitsByType(String unitType);
}
