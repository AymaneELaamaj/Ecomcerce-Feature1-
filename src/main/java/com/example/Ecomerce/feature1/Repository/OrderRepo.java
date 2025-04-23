package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}
