package com.example.propertymanagementsystem.Controller;

import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import com.example.propertymanagementsystem.Model.Subscription;
import com.example.propertymanagementsystem.Service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    // Get All Subscriptions
    @GetMapping("/get")
    public ResponseEntity<?> getAllSubscriptions() {

        return ResponseEntity.status(200).body(subscriptionService.getAllSubscriptions());
    }


    // Get Subscription By ID
    @GetMapping("/get/{subscriptionId}")
    public ResponseEntity<?> getSubscriptionById(@PathVariable Long subscriptionId) {

        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);

        return ResponseEntity.status(200).body(subscription);
    }


    // Add Subscription
    @PostMapping("/add")
    public ResponseEntity<?> addSubscription(@RequestBody @Valid Subscription subscription) {

        subscriptionService.addSubscription(subscription);

        return ResponseEntity.status(200).body(new ApiResponse("Subscription Add Successful"));
    }


    // Update Subscription
    @PutMapping("/update/{subscriptionId}")
    public ResponseEntity<?> updateSubscription(@PathVariable Long subscriptionId, @RequestBody @Valid Subscription subscription) {

        subscriptionService.updateSubscription(subscriptionId, subscription);
        return ResponseEntity.status(200).body(new ApiResponse("Subscription Update Successful"));
    }


    // Delete Subscription
    @DeleteMapping("/delete/{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId) {

        subscriptionService.deleteSubscription(subscriptionId);

        return ResponseEntity.status(200).body(new ApiResponse("Subscription Delete Successful"));
    }

    // Search subscriptions by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> searchByStatus(@PathVariable String status) {

        // Get subscriptions from service
        List<Subscription> subscriptions = subscriptionService.searchByStatus(status);
        return ResponseEntity.status(200).body(subscriptions);
    }

    //..
    // Change subscription plan
    @PutMapping("/change-plan/{subscriptionId}/{plan}/{price}")
    public ResponseEntity<?> changeSubscriptionPlan(@PathVariable Long subscriptionId, @PathVariable String plan, @PathVariable Double price) {

        subscriptionService.changeSubscriptionPlan(subscriptionId, plan, price);

        return ResponseEntity.status(200).body(new ApiResponse("Subscription plan changed successfully"));
    }

    //..

    // Renew subscription
    @PutMapping("/renew/{subscriptionId}/{newEndDate}")
    public ResponseEntity<?> renewSubscription(@PathVariable Long subscriptionId, @PathVariable LocalDate newEndDate) {

        subscriptionService.renewSubscription(subscriptionId, newEndDate);

        return ResponseEntity.status(200).body(new ApiResponse("Subscription renewed successfully"));
    }
}