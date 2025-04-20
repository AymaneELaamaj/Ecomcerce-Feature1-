package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {

}
