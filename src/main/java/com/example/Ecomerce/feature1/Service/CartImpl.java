package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Cart;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.CartRepo;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import com.example.Ecomerce.feature1.Repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImpl implements ICart {
    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private UserReop userRepository;

    @Override
    public Cart addItemtoCart(Utilsateur user, Long produitId) {

        // 🔁 On récupère le vrai utilisateur persistant depuis la DB
        Utilsateur persistentUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        // 🛒 Chercher le panier existant de l’utilisateur ou en créer un
        Cart cart = cartRepository.findByUser(persistentUser).orElse(new Cart(persistentUser));
        produit.setCart(cart); // Lien bidirectionnel pour JPA

        cart.getCartItems().add(produit);
        cart.calculateTotalPrice(); // Facultatif si tu veux recalculer à chaque ajout

        return cartRepository.save(cart);
    }


}