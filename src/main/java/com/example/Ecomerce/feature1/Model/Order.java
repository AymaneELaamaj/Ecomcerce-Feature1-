package com.example.Ecomerce.feature1.Model;

import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Component
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilsateur user; // L'utilisateur qui a passé la commande

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // To manage the forward reference of 'produits' during serialization
    private List<CartItem> produits; // Les produits de la commande, chaque produit avec une quantité

    private BigDecimal totalPrice; // Le prix total de la commande

    private String shippingAddress; // L'adresse de livraison

    private String billingAddress; // L'adresse de facturation
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Le statut de la commande (PENDING, PAID, SHIPPED, DELIVERED)

    private LocalDateTime orderDate; // Date de création de la commande

    public Order() {
    }

    public Order(Utilsateur user, List<CartItem> produits, BigDecimal totalPrice, String shippingAddress, String billingAddress, OrderStatus status) {
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
                .map(produit -> produit.getProduct().getPrice().multiply(BigDecimal.valueOf(produit.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilsateur getUser() {
        return user;
    }

    public void setUser(Utilsateur user) {
        this.user = user;
    }

    public List<CartItem> getProduits() {
        return produits;
    }

    public void setProduits(List<CartItem> produits) {
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
