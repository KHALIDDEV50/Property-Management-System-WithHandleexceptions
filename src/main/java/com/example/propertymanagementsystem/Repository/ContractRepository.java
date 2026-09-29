package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    // Search contracts by tenant ID
    @Query("SELECT c FROM Contract c WHERE c.tenantId = ?1")
    List<Contract> findByTenantId(Long tenantId);

    // Search contracts by status
    @Query("SELECT c FROM Contract c WHERE c.status = ?1")
    List<Contract> findByStatus(String status);


}
