package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    // Search subscriptions by status
    @Query("SELECT s FROM Subscription s WHERE s.status = ?1")
    List<Subscription> findByStatus(String status);
}
