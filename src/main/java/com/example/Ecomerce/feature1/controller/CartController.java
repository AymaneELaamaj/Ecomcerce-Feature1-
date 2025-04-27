package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.DTO.AddToCartRequest;
import com.example.Ecomerce.feature1.Model.Cart;
import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.CartRepo;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.Service.CartImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartImpl cartService;
    @Autowired

    private  ProduitRepository produitRepository;
    @Autowired

    private  UserReop userRepository;
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartImpl cartservice;


    @PostMapping("/add-item/{produitid}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public Cart addItemToCart(@PathVariable Long produitid, Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject(); // ou jwt.getClaim("sub")

        // Tu dois récupérer l'utilisateur depuis la BDD par son email
        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        return cartService.addItemtoCart(currentUser, produitid);
    }
    @GetMapping("/getitems")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER') or hasAuthority('SCOPE_ROLE_VENDOR')")
    public List<CartItem> getitmes(Authentication authentication){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject();
        Utilsateur currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        List<CartItem> produits = cartservice.getcartbyuser(currentUser);
        return produits;
        //return cartRepo.findAll();

    }

}
