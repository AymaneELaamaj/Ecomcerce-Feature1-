package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
