package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Owner;
import com.example.propertymanagementsystem.Repository.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    // Get All Owners
    public List<Owner> getAllOwners() {

        return ownerRepository.findAll();
    }

    // Get Owner By ID
    public Owner getOwnerById(Long ownerId) {

         ownerRepository.findById(ownerId).orElse(null);

        throw new ApiException("Owner not found");
    }
    // Add Owner
    public Owner addOwner(Owner owner) {

        boolean isExist = checkIdentificationNumber(owner.getIdentificationNumber());

        if (isExist) {throw new ApiException("Identification Number already exists");
        }

        return ownerRepository.save(owner);
    }

    // Update Owner

    public Owner updateOwner(Long ownerId, Owner owner) {

        Owner oldOwner = ownerRepository.findById(ownerId).orElse(null);

        if (oldOwner == null) {
            throw new ApiException("Owner not found");
        }

        oldOwner.setOfficeId(owner.getOfficeId());
        oldOwner.setName(owner.getName());
        oldOwner.setOwnerType(owner.getOwnerType());
        oldOwner.setIdentificationNumber(owner.getIdentificationNumber());
        oldOwner.setPhone(owner.getPhone());
        oldOwner.setEmail(owner.getEmail());
        oldOwner.setAddress(owner.getAddress());

        return ownerRepository.save(oldOwner);
    }

    // Delete Owner
    public boolean deleteOwner(Long ownerId) {

        Owner oldOwner = ownerRepository.findById(ownerId).orElse(null);

        if (oldOwner == null) {
            throw new ApiException("Owner not found");
        }

        ownerRepository.delete(oldOwner);
        return true;
    }

    // check Identification Number  for Owner
    public boolean checkIdentificationNumber(String identificationNumber) {

        List<Owner> owners =
                ownerRepository.findAll();

        for (int i = 0; i < owners.size(); i++) {

            if (owners.get(i).getIdentificationNumber()
                    .equals(identificationNumber)) {

                return true;
            }
        }

        return false;
    }

    // Search owners by office ID
    public List<Owner> searchByOfficeId(Long officeId) {

        // Call repository query
        return ownerRepository.findByOfficeId(officeId);
    }

    // Search owners by owner type
    public List<Owner> searchByOwnerType(String ownerType) {

        // Call repository query
        return ownerRepository.findByOwnerType(ownerType);
    }
}
