package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Cart;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;

import java.util.List;

public interface ICart {
    Cart addItemtoCart(Utilsateur user, Long produitI);
}
