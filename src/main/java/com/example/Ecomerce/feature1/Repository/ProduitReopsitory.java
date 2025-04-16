package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitReopsitory extends JpaRepository<Produit,Long> {
}
