package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentService implements IPaymentService{
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Override
    public void process(PaymentDTO dto) {
        if ("PAID".equals(dto.getStatus())){
            System.out.println("cette commande deja payé");
        }
        //else if (() dto.getAmount()<= dto.getOrder().getTotalPrice())
    }
}
