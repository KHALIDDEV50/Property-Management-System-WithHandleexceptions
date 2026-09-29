package com.example.propertymanagementsystem.Service;


import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Repository.ContractRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    // Get All Contracts
    public List<Contract> getAllContracts() {

        return contractRepository.findAll();
    }

    // Get Contract By ID
    public Contract getContractById(Long contractId) {

        Contract contract =
                contractRepository.findById(contractId).orElse(null);

        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        return contract;
    }

    // Add Contract
    public Contract addContract(Contract contract) {

        // Check Contract Number
        boolean isExist = checkContractNumber(contract.getContractNumber());
        if (isExist) {
            throw new ApiException("Contract Number already exists");
        }

        return contractRepository.save(contract);
    }

    // Update Contract
    public Contract updateContract(Long contractId, Contract contract) {

        Contract oldContract = contractRepository.findById(contractId).orElse(null);

        if (oldContract == null) {

            throw new ApiException("Contract not found");
        }

        oldContract.setUnitId(contract.getUnitId());
        oldContract.setTenantId(contract.getTenantId());
        oldContract.setContractNumber(contract.getContractNumber());
        oldContract.setStartDate(contract.getStartDate());
        oldContract.setEndDate(contract.getEndDate());
        oldContract.setRentAmount(contract.getRentAmount());
        oldContract.setPaymentFrequency(contract.getPaymentFrequency());
        oldContract.setStatus(contract.getStatus());
        oldContract.setNotes(contract.getNotes());

        return contractRepository.save(oldContract);
    }

    // Delete Contract
    public boolean deleteContract(Long contractId) {

        Contract oldContract =
                contractRepository.findById(contractId).orElse(null);

        if (oldContract == null) {

            throw new ApiException("Contract not found");
        }

        contractRepository.delete(oldContract);

        return true;
    }

    // check Contract Number
    public boolean checkContractNumber(String contractNumber) {

        List<Contract> contracts = contractRepository.findAll();

        for (int i = 0; i < contracts.size(); i++) {

            if (contracts.get(i).getContractNumber().equals(contractNumber)) {
                return true;
            }
        }

        return false;
    }

    // Search contracts by tenant ID
    public List<Contract> searchByTenantId(Long tenantId) {

        // Call repository query
        return contractRepository.findByTenantId(tenantId);
    }

    // Search contracts by status
    public List<Contract> searchByStatus(String status) {

        // Call repository query
        return contractRepository.findByStatus(status);
    }

    // Renew contract
    public boolean renewContract(Long contractId, LocalDate newEndDate) {

        // Get contract by ID
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Change contract end date
        contract.setEndDate(newEndDate);

        // Change contract status to ACTIVE
        contract.setStatus("ACTIVE");

        // Save updated contract
        contractRepository.save(contract);

        return true;
    }


    //..

    // Expire contract
    public boolean expireContract(Long contractId, LocalDate endDate) {

        // Get contract by ID
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Change contract end date
        contract.setEndDate(endDate);

        // Change contract status to EXPIRED
        contract.setStatus("EXPIRED");

        // Save updated contract
        contractRepository.save(contract);

        return true;
    }

    //..
    // Cancel contract
    public boolean cancelContract(Long contractId) {

        // Get contract by ID
        Contract contract =
                contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Change contract status
        contract.setStatus("CANCELLED");

        // Save updated contract
        contractRepository.save(contract);

        return true;
    }

    // Calculate remaining contract days
    public Long getRemainingContractDays(Long contractId) {

        // Get contract by ID
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Get contract end date
        LocalDate endDate = contract.getEndDate();

        // Calculate remaining days
        return ChronoUnit.DAYS.between(LocalDate.now(), endDate);
    }

    //..


    // Calculate total contract rent
    public Double calculateTotalContractRent(Long contractId) {

        // Get contract by ID
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Get rent amount
        Double rentAmount = contract.getRentAmount();

        // Get payment frequency
        String frequency = contract.getPaymentFrequency();

        // Get contract dates
        LocalDate startDate = contract.getStartDate();

        LocalDate endDate = contract.getEndDate();

        // Calculate number of payments
        long numberOfPayments = 0;

        if (frequency.equals("MONTHLY")) {

            numberOfPayments = ChronoUnit.MONTHS.between(startDate, endDate);

        } else if (frequency.equals("QUARTERLY")) {

            numberOfPayments = ChronoUnit.MONTHS.between(startDate, endDate) / 3;

        } else if (frequency.equals("SEMI_ANNUAL")) {

            numberOfPayments = ChronoUnit.MONTHS.between(startDate, endDate) / 6;

        } else if (frequency.equals("ANNUAL")) {

            numberOfPayments =ChronoUnit.YEARS.between(startDate, endDate);
        }

        // Calculate total rent
        return rentAmount * numberOfPayments;
    }



    //..

    // Check contract expiration
    public boolean checkContractExpiration(Long contractId) {

        // Get contract by ID
        Contract contract = contractRepository.findById(contractId).orElse(null);

        // Check if contract exists
        if (contract == null) {

            throw new ApiException("Contract not found");
        }

        // Get contract end date
        LocalDate endDate = contract.getEndDate();

        // Check if contract has expired
        if (endDate.isBefore(LocalDate.now())) {

            // Change contract status to EXPIRED
            contract.setStatus("EXPIRED");

            // Save updated contract
            contractRepository.save(contract);

            return true;
        }

        // Contract exists but has not expired
        throw new ApiException(
                "Contract has not expired yet"
        );
    }
}
