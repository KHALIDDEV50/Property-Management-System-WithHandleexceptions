package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Property;
import com.example.propertymanagementsystem.Repository.PropertyRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    // Get All Properties
    public List<Property> getAllProperties() {

        return propertyRepository.findAll();
    }

    // Get Property By ID
    public Property getPropertyById(Long propertyId) {

        Property property =
                propertyRepository.findById(propertyId).orElse(null);

        if (property == null) {

            throw new ApiException("Property not found");
        }

        return property;
    }

    // Add Property
    public Property addProperty(Property property) {

        return propertyRepository.save(property);
    }

    // Update Property
    public Property updateProperty(
            Long propertyId,
            Property property) {

        Property oldProperty =
                propertyRepository.findById(propertyId).orElse(null);

        if (oldProperty == null) {

            throw new ApiException("Property not found");
        }

        oldProperty.setOfficeId(property.getOfficeId());
        oldProperty.setOwnerId(property.getOwnerId());
        oldProperty.setName(property.getName());
        oldProperty.setPropertyType(property.getPropertyType());
        oldProperty.setCity(property.getCity());
        oldProperty.setAddress(property.getAddress());

        return propertyRepository.save(oldProperty);
    }

    // Delete Property
    public boolean deleteProperty(Long propertyId) {

        Property oldProperty =
                propertyRepository.findById(propertyId).orElse(null);

        if (oldProperty == null) {

            throw new ApiException("Property not found");
        }

        propertyRepository.delete(oldProperty);

        return true;
    }

    // Search properties by owner ID
    public List<Property> searchByOwnerId(Long ownerId) {

        // Call repository query
        return propertyRepository.findByOwnerId(ownerId);
    }

    // Search properties by city
    public List<Property> searchByCity(String city) {

        // Call repository query
        return propertyRepository.findByCity(city);
    }

    //..
    // Transfer property ownership
    public boolean transferPropertyOwnership(Long propertyId, Long newOwnerId) {

        // Get property by ID
        Property property = propertyRepository.findById(propertyId).orElse(null);

        // Check if property exists
        if (property == null) {

            throw new ApiException("Property not found");
        }

        // Change property owner
        property.setOwnerId(newOwnerId);

        // Save updated property
        propertyRepository.save(property);

        return true;
    }
}
