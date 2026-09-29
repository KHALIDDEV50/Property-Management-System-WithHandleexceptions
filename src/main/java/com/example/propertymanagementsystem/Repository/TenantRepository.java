package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
    // Search tenants by city
    @Query("SELECT t FROM Tenant t WHERE t.city = ?1")
    List<Tenant> findByCity(String city);

    // Search tenants by tenant type
    @Query("SELECT t FROM Tenant t WHERE t.tenantType = ?1")
    List<Tenant> findByTenantType(String tenantType);
}
