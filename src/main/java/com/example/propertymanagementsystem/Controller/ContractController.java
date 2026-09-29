package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;


    // Get All Contracts
    @GetMapping("/get")
    public ResponseEntity<?> getAllContracts() {

        return ResponseEntity.status(200).body(contractService.getAllContracts());
    }


    // Get Contract By ID
    @GetMapping("/get/{contractId}")
    public ResponseEntity<?> getContractById(@PathVariable Long contractId) {

        Contract contract = contractService.getContractById(contractId);

        return ResponseEntity.status(200).body(contract);
    }


    // Add Contract
    @PostMapping("/add")
    public ResponseEntity<?> addContract(@RequestBody @Valid Contract contract) {

        /*if (errors.hasFieldErrors()) {

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(new ApiResponse(message));
        }*/
       // boolean isExist =
             //   contractService.checkContractNumber(contract.getContractNumber());

       /* if (isExist) {
            return ResponseEntity.status(400).body(new ApiResponse("Contract Number already exists"));
        }*/
        contractService.addContract(contract);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Add Successful"));
    }


    // Update Contract
    @PutMapping("/update/{contractId}")
    public ResponseEntity<?> updateContract(@PathVariable Long contractId, @RequestBody @Valid Contract contract) {

        contractService.updateContract(contractId, contract);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Update Successful"));
    }


    // Delete Contract
    @DeleteMapping("/delete/{contractId}")
    public ResponseEntity<?> deleteContract(@PathVariable Long contractId) {

        contractService.deleteContract(contractId);

        return ResponseEntity.status(200).body(new ApiResponse("Contract Delete Successful"));
    }

    // Search contracts by tenant ID
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<?> searchByTenantId(@PathVariable Long tenantId) {
        // Get contracts from service
        List<Contract> contracts = contractService.searchByTenantId(tenantId);

        return ResponseEntity.status(200).body(contracts);
    }

    // Search contracts by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable String status) {

        // Get contracts from service
        List<Contract> contracts = contractService.searchByStatus(status);

        return ResponseEntity.status(200).body(contracts);
    }

    // Renew contract
    @PutMapping("/renew/{contractId}/{newEndDate}")
    public ResponseEntity<?> renewContract(@PathVariable Long contractId, @PathVariable LocalDate newEndDate) {

        return ResponseEntity.status(200).body(new ApiResponse("Contract renewed successfully"));
    }

    //..

    // Expire contract
    @PutMapping("/expire/{contractId}/{endDate}")
    public ResponseEntity<?> expireContract(@PathVariable Long contractId, @PathVariable LocalDate endDate) {

        return ResponseEntity.status(200).body(new ApiResponse("Contract expired successfully"));
    }

    //..
    // Cancel contract
    @PutMapping("/cancel/{contractId}")
    public ResponseEntity<?> cancelContract(@PathVariable Long contractId) {

        return ResponseEntity.status(200).body(new ApiResponse("Contract cancelled successfully"));
    }

    //..
    // Calculate remaining contract days
    @GetMapping("/remaining-days/{contractId}")
    public ResponseEntity<?> getRemainingContractDays(@PathVariable Long contractId) {

        // Get remaining contract days
        Long remainingDays = contractService.getRemainingContractDays(contractId);
        return ResponseEntity.status(200).body(remainingDays);
    }

    //..

    // Calculate total contract rent
    @GetMapping("/total-rent/{contractId}")
    public ResponseEntity<?> calculateTotalContractRent(@PathVariable Long contractId) {

        // Calculate total contract rent
        Double totalRent = contractService.calculateTotalContractRent(contractId);

        return ResponseEntity.status(200).body(totalRent);
    }

    //..
    // Check contract expiration
    @PutMapping("/check-expiration/{contractId}")
    public ResponseEntity<?> checkContractExpiration(@PathVariable Long contractId) {

        return ResponseEntity.status(200).body(new ApiResponse("Contract status changed to EXPIRED"));
    }
}