package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepo extends JpaRepository<CartItem,Long> {
}
