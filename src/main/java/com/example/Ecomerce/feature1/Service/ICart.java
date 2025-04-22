package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Cart;

public interface ICart {
    Cart addItemtoCart(Long userId, Long produitId, int quantity);
}
