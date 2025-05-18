package com.example.Ecomerce.feature1.Model;

import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Component
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String paymentMethod; // Ex: "Credit Card", "PayPal", "Cash On Delivery"
    @Enumerated(EnumType.STRING)

    private PaymentStatuts status; // "PENDING", "SUCCESS", "FAILED"

    private LocalDateTime paymentDate;

    @OneToOne
    private Order order;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatuts getStatus() {
        return status;
    }

    public void setStatus(PaymentStatuts status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
