package com.example.Ecomerce.feature1.DTO;

import com.example.Ecomerce.feature1.Model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long id;
    private String shippingAddress;
    private String billingAddress;
    private BigDecimal totalPrice;
    private List<CartItem> produits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getProduits() {
        return produits;
    }

    public void setProduits(List<CartItem> produits) {
        this.produits = produits;
    }
// Add getters and setters
}



