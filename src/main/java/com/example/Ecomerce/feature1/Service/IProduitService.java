package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Produit;

import java.util.List;

public interface IProduitService {
    Produit Saveproduit(Produit produit);
    void Deleteproduit(Long id);
    Produit Updateproduit(Long id,Produit produit);
    List<Produit> ProduitList();
}
