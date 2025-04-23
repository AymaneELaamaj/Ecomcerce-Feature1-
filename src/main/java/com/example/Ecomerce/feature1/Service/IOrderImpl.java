package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class IOrderImpl implements IOrder{
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private UserReop userRepository;
    @Autowired
    private CartImpl cartservice;
    @Override
    public Order CreateOrder(Order order, Utilsateur utilsateur,String shippingAddress, String billingAddress) {
        Utilsateur user = userRepository.findById(utilsateur.getId()).orElseThrow(() -> new
                RuntimeException("utilsateur intouvable"));
        List<Produit> produits = cartservice.getcartbyuser(user);

        // Associer les produits à la commande
        order.setProduits(produits);

        // Calculer le prix total
        order.calculateTotalPrice();

        // Assigner les adresses
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);

        // Définir le statut par défaut à PENDING
        order.setStatus(OrderStatus.PENDING);

        // Définir la date de la commande
        order.setOrderDate(LocalDateTime.now());

        // Associer l'utilisateur à la commande
        order.setUser(user);

        // Sauvegarder la commande
        return orderRepository.save(order);



    }

    @Override
    public void DeleteOrder(Long id) {

    }

    @Override
    public Order GetOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new RuntimeException("Order not exists"));
    }
}
