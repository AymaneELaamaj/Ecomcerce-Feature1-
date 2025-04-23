package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.OrderRequest;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.Service.IOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Order")
public class OrderController {
    @Autowired
    private IOrderImpl orderservice;
    @Autowired
    private UserReop userRepository;
    @PostMapping("/createorder")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public Order createorder(@RequestBody OrderRequest order, Authentication authentication){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject();
        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return orderservice.CreateOrder(order.getOrder(),currentUser,order.getShippingAddress(),order.getBillingAddress());

    }

}
