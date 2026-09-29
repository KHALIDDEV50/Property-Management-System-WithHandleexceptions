package com.example.propertymanagementsystem.Repository;

import com.example.propertymanagementsystem.Model.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule,Long> {

    // Search payment schedules by contract ID
    @Query("SELECT p FROM PaymentSchedule p WHERE p.contractId = ?1")
    List<PaymentSchedule> findByContractId(Long contractId);

    // Search payment schedules by status
    @Query("SELECT p FROM PaymentSchedule p WHERE p.status = ?1")
    List<PaymentSchedule> findByStatus(String status);

    // Search payment schedules by due date
    @Query("SELECT p FROM PaymentSchedule p WHERE p.dueDate = ?1")
    List<PaymentSchedule> findByDueDate(LocalDate dueDate);

    // Get the latest payment schedule for a contract
    @Query("SELECT p FROM PaymentSchedule p WHERE p.contractId = ?1 ORDER BY p.dueDate DESC")
    List<PaymentSchedule> getLatestPayment(Long contractId);
}
