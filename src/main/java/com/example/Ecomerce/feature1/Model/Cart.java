package com.example.Ecomerce.feature1.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // Si vous exposez le Cart via API
    private Utilsateur user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produit> cartItems = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = "promo_code")
    private String promoCode;

    private BigDecimal discount = BigDecimal.ZERO;

    public void calculateTotalPrice() {
        this.totalPrice = cartItems.stream()
                .map(item -> item.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .subtract(discount);
    }

    public void clearCart() {
        this.cartItems.clear();
        this.totalPrice = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
        this.promoCode = null;
    }

    public Cart(Utilsateur user) {
        this.user = user;
        this.cartItems = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
        this.discount = BigDecimal.ZERO;
    }
    public Cart(){}

    public Utilsateur getUser() {
        return user;
    }

    public void setUser(Utilsateur user) {
        this.user = user;
    }

    public List<Produit> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Produit> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

}
