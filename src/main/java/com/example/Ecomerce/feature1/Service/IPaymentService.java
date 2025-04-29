package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Model.Payment;

public interface IPaymentService {
    Payment process(PaymentDTO dto);
}
