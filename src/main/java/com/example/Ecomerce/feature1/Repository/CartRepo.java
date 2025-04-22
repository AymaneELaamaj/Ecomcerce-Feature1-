package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
}
