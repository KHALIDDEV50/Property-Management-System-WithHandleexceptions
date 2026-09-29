package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {

    // Search owners by office ID
    @Query("SELECT o FROM Owner o WHERE o.officeId = ?1")
    List<Owner> findByOfficeId(Long officeId);

    // Search owners by owner type
    @Query("SELECT o FROM Owner o WHERE o.ownerType = ?1")
    List<Owner> findByOwnerType(String ownerType);
}
