package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Model.Payment;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import org.springframework.security.core.Authentication;

public interface IPaymentService {
    PaymentDTO process( Utilsateur user,String paymentMethod);
}
