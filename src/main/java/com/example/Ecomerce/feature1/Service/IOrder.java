package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.DTO.OrderDTO;
import com.example.Ecomerce.feature1.DTO.OrderItemDTO;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Utilsateur;

import java.util.List;

public interface IOrder {
    OrderDTO CreateOrder(String email, String shippingAddress, String billingAddress);
    void DeleteOrder(Long id);
    Order getOrder(Long id);
}
