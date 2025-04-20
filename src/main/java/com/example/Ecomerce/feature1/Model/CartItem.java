package com.example.Ecomerce.feature1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "produit_id") // Changé pour correspondre à Produit
    private Produit produit; // Changé de 'product' à 'produit'

    private int quantity;

    public CartItem(Cart cart, Produit produit, int quantity) {
        this.cart = cart;
        this.produit = produit;
        this.quantity = quantity;
    }
    public CartItem(){}

    /*public void updateQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = newQuantity;
    }

    // Méthode pratique pour calculer le sous-total
    public BigDecimal getSubTotal() {
        return produit.getPrice().multiply(BigDecimal.valueOf(quantity));
    }*/

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
