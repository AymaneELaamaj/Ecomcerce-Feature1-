package com.example.Ecomerce.feature1.mapper;


import com.example.Ecomerce.feature1.DTO.OrderDTO;
import com.example.Ecomerce.feature1.DTO.ProduitDTO;
import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Produit;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setBillingAddress(order.getBillingAddress());
        dto.setTotalPrice(order.getTotalPrice());

        // Map des CartItems
        List<CartItem> cartItems = order.getProduits()
                .stream()
                .collect(Collectors.toList());  // On garde les CartItems directement

        dto.setProduits(cartItems);  // On assigne directement la liste des CartItems à l'OrderDTO

        return dto;
    }

    private static ProduitDTO mapProduitToProduitDTO(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setName(produit.getName());
        produitDTO.setPrice(produit.getPrice());
        produitDTO.setQuantity(produit.getQuantity());

        return produitDTO;
    }
}

