package com.example.Ecomerce.feature1.DTO;

import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import com.example.Ecomerce.feature1.Model.Order;

import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;

    private double amount;

    private String paymentMethod; // Ex: "Credit Card", "PayPal", "Cash On Delivery"

    private PaymentStatuts status; // "PENDING", "SUCCESS", "FAILED"

    private LocalDateTime paymentDate;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

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
}
