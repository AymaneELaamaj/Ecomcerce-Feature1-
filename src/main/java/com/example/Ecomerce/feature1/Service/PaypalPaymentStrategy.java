package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaypalPaymentStrategy implements PaymentStrategy {
    @Override
    public Payment pay(Order order) {
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice().doubleValue());
        payment.setPaymentMethod("PAYPAL");
        payment.setStatus(PaymentStatuts.Success);
        payment.setPaymentDate(LocalDateTime.now());
        return payment;
    }

    @Override
    public String getMethod() {
        return "PAYPAL";
    }
}

