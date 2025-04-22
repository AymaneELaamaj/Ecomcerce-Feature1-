package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.CartItem;
import com.example.Ecomerce.feature1.Model.Produit;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IProduitService {
    Produit Saveproduit(Produit produit, Authentication authentication);
    void Deleteproduit(Long id,Authentication authentication);
    Produit Updateproduit(Long id,Produit produit,Authentication authentication);
    List<Produit> ProduitList();
    CartItem addProduittoCart(Produit produit);
}
