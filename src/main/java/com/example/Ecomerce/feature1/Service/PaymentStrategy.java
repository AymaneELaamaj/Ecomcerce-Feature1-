package com.example.Ecomerce.feature1.Service;


import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Payment;

public interface PaymentStrategy {
    Payment pay(Order order);
    String getMethod(); // pour identifier la stratégie
}

