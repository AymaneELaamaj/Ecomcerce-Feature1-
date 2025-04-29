package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Eums.OrderStatus;
import com.example.Ecomerce.feature1.Model.Order;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order,Long> {
    Optional<Order> findTopByUserAndStatus(Utilsateur user, OrderStatus status);

}
