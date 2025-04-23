package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Utilsateur;

public interface IOrder {
    Order CreateOrder(Order order, Utilsateur utilsateur, String shippingAddress, String billingAddress);
    void DeleteOrder(Long id);
    Order GetOrder(Long id);
}
