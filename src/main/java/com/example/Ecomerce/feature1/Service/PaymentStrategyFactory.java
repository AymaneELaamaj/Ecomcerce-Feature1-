package com.example.Ecomerce.feature1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> strategies = new HashMap<>();

    @Autowired
    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        for (PaymentStrategy strategy : strategyList) {
            strategies.put(strategy.getMethod(), strategy);
        }
    }

    public PaymentStrategy getStrategy(String method) {
        if (!strategies.containsKey(method)) {
            throw new IllegalArgumentException("Méthode de paiement non supportée: " + method);
        }
        return strategies.get(method);
    }
}

