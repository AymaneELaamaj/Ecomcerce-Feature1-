package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.OrderDTO;
import com.example.Ecomerce.feature1.DTO.OrderItemDTO;
import com.example.Ecomerce.feature1.DTO.UserDTO;
import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.CartItemsRepo;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class IOrderImpl implements IOrder{
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private UserReop userRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CartImpl cartservice;
    @Autowired
    CartItemsRepo cartItemsRepo;

    @Override
    public OrderDTO CreateOrder(String email, String shippingAddress, String billingAddress) {
        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        UserDTO userDTO = new UserDTO(currentUser.getEmail());

        List<CartItem> produits = cartservice.getcartbyuser(currentUser);

        if (produits.isEmpty()) {
            throw new RuntimeException("Votre panier est vide !");
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<CartItem> produitsCommandes = new ArrayList<>();

        for (CartItem produit : produits) {
            if (produit.getQuantity() <= 0) {
                throw new RuntimeException("Produit " + produit.getProduct().getName() + " a une quantité invalide");
            }
            if (produit.getProduct().getStock() < produit.getQuantity()) {
                throw new RuntimeException("Stock insuffisant pour : " + produit.getProduct().getName());
            }

            BigDecimal produitTotal = produit.getProduct().getPrice().multiply(BigDecimal.valueOf(produit.getQuantity()));
            totalPrice = totalPrice.add(produitTotal);

            produit.getProduct().setStock(produit.getProduct().getStock() - produit.getQuantity());
            produitsCommandes.add(produit);
        }

        Order order = new Order();
        order.setUser(currentUser);
        order.setProduits(produitsCommandes);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        cartItemsRepo.saveAll(produitsCommandes);

        //cartservice.clearCart(currentUser); // vider le panier
        OrderDTO savedOrderdto = OrderMapper.toDTO(savedOrder);
        cartservice.clearCart(currentUser);


        return savedOrderdto;
    }

    @Override
    public void DeleteOrder(Long id) {

    }

    @Override
    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not exists"));

        return order; // On utilise le mapper ici
    }

}
