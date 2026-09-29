package com.example.propertymanagementsystem.Service;

import com.example.propertymanagementsystem.Model.Contract;
import com.example.propertymanagementsystem.Model.PaymentSchedule;
import com.example.propertymanagementsystem.Model.Unit;
import com.example.propertymanagementsystem.Repository.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ManagementOfficeRepository managementOfficeRepository;
    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final UnitRepository unitRepository;
    private final TenantRepository tenantRepository;
    private final ContractRepository contractRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;
    private final SubscriptionRepository subscriptionRepository;

    // OpenAI Service
    private final OpenAIService openAIService;

    // Email Service
    private final EmailService emailService;


    // Get PMS report data
    public String getReportData() {

        // Get all units
        List<Unit> units = unitRepository.findAll();

        // Get all contracts
        List<Contract> contracts = contractRepository.findAll();

        // Get all payments
        List<PaymentSchedule> payments =
                paymentScheduleRepository.findAll();


        // =========================
        // Basic Statistics
        // =========================

        long offices =
                managementOfficeRepository.count();

        long owners =
                ownerRepository.count();

        long properties =
                propertyRepository.count();

        long tenants =
                tenantRepository.count();

        long subscriptions =
                subscriptionRepository.count();


        // =========================
        // Unit Statistics
        // =========================

        Map<String, Integer> unitStatusCount =
                new HashMap<>();


        for (Unit unit : units) {

            String status = unit.getStatus();

            if (status != null) {

                if (unitStatusCount.containsKey(status)) {

                    unitStatusCount.put(
                            status,
                            unitStatusCount.get(status) + 1
                    );

                } else {

                    unitStatusCount.put(
                            status,
                            1
                    );
                }
            }
        }


        // =========================
        // Contract Statistics
        // =========================

        Map<String, Integer> contractStatusCount =
                new HashMap<>();

        double totalContractRent = 0;


        for (Contract contract : contracts) {

            // Get contract status
            String status = contract.getStatus();


            // Count contract status
            if (status != null) {

                if (contractStatusCount.containsKey(status)) {

                    contractStatusCount.put(
                            status,
                            contractStatusCount.get(status) + 1
                    );

                } else {

                    contractStatusCount.put(
                            status,
                            1
                    );
                }
            }


            // Calculate contract rent
            if (contract.getRentAmount() != null) {

                totalContractRent +=
                        contract.getRentAmount();
            }
        }


        // =========================
        // Payment Statistics
        // =========================

        Map<String, Integer> paymentStatusCount =
                new HashMap<>();

        double totalPaymentAmount = 0;
        double totalPaidAmount = 0;
        double totalPendingAmount = 0;
        double totalOverdueAmount = 0;


        for (PaymentSchedule payment : payments) {

            // Get payment status
            String status = payment.getStatus();


            // Count payment status
            if (status != null) {

                if (paymentStatusCount.containsKey(status)) {

                    paymentStatusCount.put(
                            status,
                            paymentStatusCount.get(status) + 1
                    );

                } else {

                    paymentStatusCount.put(
                            status,
                            1
                    );
                }
            }


            // Get payment amount
            if (payment.getAmount() != null) {

                double amount =
                        payment.getAmount();


                // Total payment amount
                totalPaymentAmount += amount;


                // Paid
                if ("PAID".equals(status)) {

                    totalPaidAmount += amount;
                }


                // Pending
                else if ("PENDING".equals(status)) {

                    totalPendingAmount += amount;
                }


                // Overdue
                else if ("OVERDUE".equals(status)) {

                    totalOverdueAmount += amount;
                }
            }
        }


        // =========================
        // Create Report Data
        // =========================

        String data =

                "بيانات نظام إدارة الأملاك\n\n" +

                        "=== الإحصائيات العامة ===\n" +

                        "عدد مكاتب إدارة الأملاك: "
                        + offices + "\n" +

                        "عدد الملاك: "
                        + owners + "\n" +

                        "عدد العقارات: "
                        + properties + "\n" +

                        "عدد الوحدات: "
                        + units.size() + "\n" +

                        "عدد المستأجرين: "
                        + tenants + "\n" +

                        "عدد الاشتراكات: "
                        + subscriptions + "\n\n" +


                        "=== الوحدات حسب الحالة ===\n" +

                        "تفاصيل حالات الوحدات: "
                        + unitStatusCount + "\n\n" +


                        "=== العقود ===\n" +

                        "إجمالي العقود: "
                        + contracts.size() + "\n" +

                        "العقود حسب الحالة: "
                        + contractStatusCount + "\n" +

                        "مجموع مبالغ الإيجار المسجلة في العقود: "
                        + totalContractRent
                        + " SAR\n\n" +


                        "=== المدفوعات ===\n" +

                        "إجمالي جداول الدفع: "
                        + payments.size() + "\n" +

                        "جداول الدفع حسب الحالة: "
                        + paymentStatusCount + "\n" +

                        "إجمالي مبالغ جداول الدفع: "
                        + totalPaymentAmount
                        + " SAR\n" +

                        "إجمالي المدفوعات المسددة: "
                        + totalPaidAmount
                        + " SAR\n" +

                        "إجمالي المدفوعات المعلقة: "
                        + totalPendingAmount
                        + " SAR\n" +

                        "إجمالي المدفوعات المتأخرة: "
                        + totalOverdueAmount
                        + " SAR\n";


        // Return report data
        return data;
    }


    // Generate AI report
    public String generateAIReport() {

        // Get PMS data
        String data = getReportData();


        // Send data to OpenAI
        String aiReport =
                openAIService.generateReport(data);


        // Return AI report
        return aiReport;
    }


    // Generate AI report and send it by email
    public boolean generateAndSendAIReport(
            String email) throws MessagingException {

        // Get PMS data
        String data = getReportData();


        // Generate AI HTML report
        String aiReport =
                openAIService.generateReport(data);


        // Send HTML report by email
        emailService.sendEmail(
                email,
                "تقرير نظام إدارة الأملاك",
                aiReport
        );

        // Email sent successfully
        return true;
    }
}