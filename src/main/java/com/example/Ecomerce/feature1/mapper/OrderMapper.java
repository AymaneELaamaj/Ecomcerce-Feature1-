package com.example.Ecomerce.feature1.mapper;


import com.example.Ecomerce.feature1.DTO.OrderDTO;
import com.example.Ecomerce.feature1.DTO.PaymentDTO;
import com.example.Ecomerce.feature1.DTO.ProduitDTO;
import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Payment;
import com.example.Ecomerce.feature1.Model.Produit;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
       OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(order,orderDTO);
        orderDTO.setProduits(order.getProduits());

        return orderDTO;
    }

    private static ProduitDTO mapProduitToProduitDTO(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setName(produit.getName());
        produitDTO.setPrice(produit.getPrice());
        produitDTO.setQuantity(produit.getQuantity());

        return produitDTO;
    }
    public static PaymentDTO mapToPaymentDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        return paymentDTO;
    }
}

