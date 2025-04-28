package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.OrderDTO;
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
    public OrderDTO createOrder(@RequestBody OrderRequest orderRequest, Authentication authentication) {
        // 1. Récupérer l'utilisateur actuel à partir du token
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject();

        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        // 2. Appeler directement ton service CreateOrder
        return orderservice.CreateOrder(
                email,
                orderRequest.getShippingAddress(),
                orderRequest.getBillingAddress()
        );
    }

    @GetMapping("/getorder/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public OrderDTO getorderbyid(@PathVariable Long id){
        return orderservice.getOrder(id);

    }

}
