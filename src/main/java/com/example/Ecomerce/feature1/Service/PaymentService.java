package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Payment;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentService implements IPaymentService{
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Override
    public Payment process(PaymentDTO dto) {
        Order order = orderRepo.findById(dto.getOrder().getId())
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("PAID".equals(order.getStatus())){
            System.out.println("cette commande deja payé");
        }

        else if (BigDecimal.valueOf(dto.getAmount()).compareTo(dto.getOrder().getTotalPrice())<=0){
            System.out.println( " votrs solde est insufissant");
        }
        Payment payment = new Payment();
        payment.setOrder(dto.getOrder());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setStatus(PaymentStatuts.Success); // tu peux gérer l’état plus finement plus tard
        payment.setPaymentDate(LocalDateTime.now());

        // Sauvegarde du paiement
        return paymentRepo.save(payment);
    }
}
