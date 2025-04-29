package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Model.Payment;
import com.example.Ecomerce.feature1.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public ResponseEntity<Payment> pay(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = paymentService.process(paymentDTO);
        return ResponseEntity.ok(payment);
    }
}

