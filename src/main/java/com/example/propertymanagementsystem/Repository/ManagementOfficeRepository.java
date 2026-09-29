package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.ManagementOffice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagementOfficeRepository extends JpaRepository<ManagementOffice, Long> {


    // Search for management offices by city
    @Query("SELECT o FROM ManagementOffice o WHERE o.city = :city")
    List<ManagementOffice> findByCity(@Param("city") String city);
}
