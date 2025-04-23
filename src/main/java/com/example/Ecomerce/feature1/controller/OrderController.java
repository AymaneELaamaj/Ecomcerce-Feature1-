package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.OrderRequest;
import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.OrderRepo;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.Service.CartImpl;
import com.example.Ecomerce.feature1.Service.IOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private IOrderImpl orderservice;
    @Autowired
    private UserReop userRepository;
    @Autowired
    private OrderRepo orderRepository;
    @Autowired
    private CartImpl cartservice;

    @PostMapping("/createorder")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public Order createorder(@RequestBody OrderRequest orderRequest, Authentication authentication){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject();
        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        List<Produit> produits = cartservice.getcartbyuser(currentUser); // This should be a method to get the cart's products

        Order order = new Order();
        order.setUser(currentUser);
        order.setProduits(produits);
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setBillingAddress(orderRequest.getBillingAddress());
        order.setStatus(OrderStatus.PENDING);
        order.calculateTotalPrice();  // Calculate the total price of the order
        order.setOrderDate(LocalDateTime.now());

        // Save the order to the database
        return orderRepository.save(order);

    }
    @GetMapping("/getorder/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")

    public Order getorderbyid(@PathVariable Long id){
        return orderservice.GetOrder(id);

    }

}
