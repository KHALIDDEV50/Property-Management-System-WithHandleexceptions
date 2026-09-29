package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {

    // Search properties by owner ID
    @Query("SELECT p FROM Property p WHERE p.ownerId = ?1")
    List<Property> findByOwnerId(Long ownerId);

    // Search properties by city
    @Query("SELECT p FROM Property p WHERE p.city = ?1")
    List<Property> findByCity(String city);
}
