package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Tenant;
import com.example.propertymanagementsystem.Repository.TenantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    // Get All Tenants
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    // Get Tenant By ID
    public Tenant getTenantById(Long tenantId) {

        Tenant tenant =
                tenantRepository.findById(tenantId).orElse(null);

        if (tenant == null) {

            throw new ApiException("Tenant not found");
        }

        return tenant;
    }

    // Add Tenant
    public Tenant addTenant(Tenant tenant) {

        boolean isExist = checkIdentificationNumber(tenant.getIdentificationNumber());

        if (isExist) {
            throw new ApiException("Identification Number already exists");
        }

        return tenantRepository.save(tenant);
    }

    // Update Tenant
    public Tenant updateTenant(Long tenantId, Tenant tenant) {

        Tenant oldTenant = tenantRepository.findById(tenantId).orElse(null);

        if (oldTenant == null) {

            throw new ApiException("Tenant not found");
        }

        oldTenant.setName(tenant.getName());
        oldTenant.setTenantType(tenant.getTenantType());
        oldTenant.setIdentificationNumber(
                tenant.getIdentificationNumber()
        );
        oldTenant.setPhone(tenant.getPhone());
        oldTenant.setEmail(tenant.getEmail());
        oldTenant.setCity(tenant.getCity());
        oldTenant.setAddress(tenant.getAddress());

        return tenantRepository.save(oldTenant);
    }

    // Delete Tenant
    public boolean deleteTenant(Long tenantId) {

        Tenant oldTenant = tenantRepository.findById(tenantId).orElse(null);

        if (oldTenant == null) {

            throw new ApiException("Tenant not found");
        }

        tenantRepository.delete(oldTenant);

        return true;
    }

    //  check Identification Number for Tenant
    public boolean checkIdentificationNumber(String identificationNumber) {

        List<Tenant> tenants = tenantRepository.findAll();

        for (int i = 0; i < tenants.size(); i++) {

            if (tenants.get(i).getIdentificationNumber()
                    .equals(identificationNumber)) {

                return true;
            }
        }

        return false;
    }

    // Search tenants by city
    public List<Tenant> searchByCity(String city) {

        // Call repository query
        return tenantRepository.findByCity(city);
    }

    // Search tenants by tenant type
    public List<Tenant> searchByTenantType(String tenantType) {

        // Call repository query
        return tenantRepository.findByTenantType(tenantType);
    }
}
