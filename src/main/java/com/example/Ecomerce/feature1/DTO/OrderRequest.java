package com.example.Ecomerce.feature1.DTO;

import com.example.Ecomerce.feature1.Model.Order;

public class OrderRequest {

    private String shippingAddress;
    private String billingAddress;

    // Getters et setters


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
}

