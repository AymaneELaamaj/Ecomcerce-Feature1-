package com.example.Ecomerce.feature1.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilsateur user; // L'utilisateur qui a passé la commande

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Produit> produits; // Les produits de la commande, chaque produit avec une quantité

    private BigDecimal totalPrice; // Le prix total de la commande

    private String shippingAddress; // L'adresse de livraison

    private String billingAddress; // L'adresse de facturation

    private String status; // Le statut de la commande (PENDING, PAID, SHIPPED, DELIVERED)

    private LocalDateTime orderDate; // Date de création de la commande

    public Order() {
    }

    public Order(Utilsateur user, List<Produit> produits, BigDecimal totalPrice, String shippingAddress, String billingAddress, String status) {
        this.user = user;
        this.produits = produits;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.status = status;
        this.orderDate = LocalDateTime.now();
    }
    public void calculateTotalPrice() {
        this.totalPrice = produits.stream()
                .map(produit -> produit.getPrice().multiply(BigDecimal.valueOf(produit.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Utilsateur getUser() {
        return user;
    }

    public void setUser(Utilsateur user) {
        this.user = user;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}

