package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.Model.Subscription;
import com.example.propertymanagementsystem.Repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    // Get All Subscriptions
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }


    // Get Subscription By ID
    public Subscription getSubscriptionById(Long subscriptionId) {

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (subscription == null) {

            throw new ApiException("Subscription not found");
        }

        return subscription;
    }

    // Add Subscription
    public Subscription addSubscription(Subscription subscription) {

        return subscriptionRepository.save(subscription);
    }

    // Update Subscription
    public Subscription updateSubscription(Long subscriptionId, Subscription subscription) {

        Subscription oldSubscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (oldSubscription == null) {
            throw new ApiException("Subscription not found");
        }

        oldSubscription.setOfficeId(subscription.getOfficeId());

        oldSubscription.setPlan(subscription.getPlan());

        oldSubscription.setStartDate(subscription.getStartDate());

        oldSubscription.setEndDate(subscription.getEndDate());

        oldSubscription.setPrice(subscription.getPrice());

        oldSubscription.setStatus(subscription.getStatus());

        return subscriptionRepository.save(oldSubscription);
    }

    // Delete Subscription
    public boolean deleteSubscription(Long subscriptionId) {

        Subscription oldSubscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (oldSubscription == null) {

            throw new ApiException("Subscription not found");
        }

        subscriptionRepository.delete(oldSubscription);

        return true;
    }

    // Search subscriptions by status
    public List<Subscription> searchByStatus(String status) {

        // Call repository query
        return subscriptionRepository.findByStatus(status);
    }

    //..
    // Change subscription plan
    public boolean changeSubscriptionPlan(Long subscriptionId, String plan, Double price) {

        // Get subscription by ID
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        // Check if subscription exists
        if (subscription == null) {
            throw new ApiException("Subscription not found");
        }

        // Change subscription plan
        subscription.setPlan(plan);

        // Change subscription price
        subscription.setPrice(price);

        // Save updated subscription
        subscriptionRepository.save(subscription);

        return true;
    }

    //..
    // Renew subscription
    public boolean renewSubscription(Long subscriptionId, LocalDate newEndDate) {

        // Get subscription by ID
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        // Check if subscription exists
        if (subscription == null) {

            throw new ApiException("Subscription not found");
        }

        // Change subscription end date
        subscription.setEndDate(newEndDate);

        // Change subscription status to ACTIVE
        subscription.setStatus("ACTIVE");

        // Save updated subscription
        subscriptionRepository.save(subscription);

        return true;
    }
}
