package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.example.Ecomerce.feature1.Eums.PaymentStatuts;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Payment;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.PaymentRepo;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional
@Component
public class PaymentService implements IPaymentService{
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserReop userRepository;
    @Autowired private PaymentStrategyFactory strategyFactory;

    @Override
    public PaymentDTO process( Utilsateur user,String paymentMethod) {

        Utilsateur currentuser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Order order = orderRepo.findTopByUserAndStatus(currentuser, OrderStatus.PENDING)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        if ("PAID".equals(order.getStatus())){
            System.out.println("cette commande deja payé");
        }

        /*else if (BigDecimal.valueOf(currentuser..getAmount()).compareTo(dto.getOrder().getTotalPrice())<=0){
            System.out.println( " votrs solde est insufissant");
        }*/
        PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethod);
        Payment payment = strategy.pay(order);


        // Sauvegarde du paiement
        payment = paymentRepo.save(payment);

        // Retourner un PaymentDTO
        return OrderMapper.mapToPaymentDTO(payment);
    }
}
