package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Cart;
import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.CartItemsRepo;
import com.example.Ecomerce.feature1.Repository.CartRepo;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import com.example.Ecomerce.feature1.Repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CartImpl implements ICart {
    @Autowired
    private CartRepo cartRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private UserReop userRepository;
    @Autowired
    private CartItemsRepo cartItemsRepository;
    @Override
    public void clearCart(Utilsateur user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        cart.getCartItems().clear(); // Vide la liste des items du panier
        cartRepository.save(cart);     // Sauvegarde le panier vide
    }

    @Override
    public Cart addItemtoCart(Utilsateur user, Long produitId) {

        // 🔁 On récupère le vrai utilisateur persistant depuis la DB
        Utilsateur persistentUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        // 🛒 Chercher le panier existant de l’utilisateur ou en créer un
        Cart cart = cartRepository.findByUser(persistentUser).orElse(new Cart(persistentUser));
        //produit.setCart(cart); // Lien bidirectionnel pour JPA
        CartItem cartItem = new CartItem(produit,1);  // 1 est la quantité par défaut

        cartItem.setCart(cart);
        //cartItem.setQuantity(1);


        cart.getCartItems().add(cartItem);
        cart.calculateTotalPrice(); // Facultatif si tu veux recalculer à chaque ajout

        return cartRepository.save(cart);
    }

    @Override
    public List<CartItem> getcartbyuser(Utilsateur user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Panier introuvable"));
        return cart.getCartItems(); // Retourne la liste des produits dans le panier

    }


}