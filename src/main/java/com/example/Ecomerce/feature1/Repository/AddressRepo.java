package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
