package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProduitReopsitory extends JpaRepository<Produit,Long> {

}
