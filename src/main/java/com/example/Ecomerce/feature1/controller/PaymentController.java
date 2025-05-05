package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.DTO.PaymentRequestDTO;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserReop userRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public ResponseEntity<PaymentDTO> pay(@RequestBody PaymentRequestDTO request, Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject(); // ou jwt.getClaim("sub")

        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        // Appelle du service avec la méthode dynamique
        PaymentDTO paymentDTO = paymentService.process(currentUser, request.getPaymentMethod().toUpperCase());

        return ResponseEntity.ok(paymentDTO);
    }
}
