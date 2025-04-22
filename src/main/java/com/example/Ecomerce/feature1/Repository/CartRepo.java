package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Cart;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(Utilsateur user);
}
