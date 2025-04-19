package com.example.Ecomerce.feature1.Repository;

import com.example.Ecomerce.feature1.Model.Utilsateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReop extends JpaRepository<Utilsateur,Long> {
    Optional<Utilsateur> findByEmail(String email); // Requête pour loadUserByUsername

}
