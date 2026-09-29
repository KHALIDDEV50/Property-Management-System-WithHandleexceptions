package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.ManagementOffice;
import com.example.propertymanagementsystem.Repository.ManagementOfficeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagementOfficeService {

    private final ManagementOfficeRepository managementOfficeRepository;

    // Get All Office...

    public List<ManagementOffice> getAllOffice() {
        return managementOfficeRepository.findAll();
    }

    // Get Office By ID. add Handle our custom exceptions
    public ManagementOffice getOfficeById(Long officeId) {

        ManagementOffice office = managementOfficeRepository.findById(officeId).orElse(null);
        if (office == null) {

            throw new ApiException("Office not found");
        }
        return office;
    }

    // Add Office
    public ManagementOffice addOffice(ManagementOffice managementOffice) {
        return managementOfficeRepository.save(managementOffice);
    }

    // Update Office
    public ManagementOffice updateOffice(Long officeId, ManagementOffice managementOffice) {

        ManagementOffice oldOffice = managementOfficeRepository.findById(officeId).orElse(null);

        if (oldOffice == null) {
            throw new ApiException("Office not found");
        }

        oldOffice.setName(managementOffice.getName());
        oldOffice.setCommercialRegistration(managementOffice.getCommercialRegistration());
        oldOffice.setPhone(managementOffice.getPhone());
        oldOffice.setEmail(managementOffice.getEmail());
        oldOffice.setCity(managementOffice.getCity());
        oldOffice.setAddress(managementOffice.getAddress());
        oldOffice.setStatus(managementOffice.getStatus());

        return managementOfficeRepository.save(oldOffice);
    }

    // Delete Office
    public void deleteOffice(Long officeId) {

        ManagementOffice oldOffice = managementOfficeRepository.findById(officeId).orElse(null);

        if (oldOffice == null) {
            throw new ApiException("Office not found");
        }

        managementOfficeRepository.delete(oldOffice);

    }
    // check Commercial Registration
    public boolean checkCommercialRegistration(String commercialRegistration) {
        List<ManagementOffice> offices = managementOfficeRepository.findAll();
        for (int i = 0; i < offices.size(); i++) {
            if (offices.get(i).getCommercialRegistration().equals(commercialRegistration)) {
                return true;
            }

        }
        return false;
    }

    // Search offices by city

    public List<ManagementOffice> searchByCity(String city) {

        // Call the repository query
        return managementOfficeRepository.findByCity(city);
    }

}
