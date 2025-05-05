package com.example.Ecomerce.feature1.DTO;


public class PaymentRequestDTO {
    private String paymentMethod; // "CARD", "PAYPAL", etc.

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

