package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import com.example.Ecomerce.feature1.Repository.UserReop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ProduitSecurityService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private UserReop userReop;
    /**
     * Vérifie si l'utilisateur est admin OU propriétaire du produit
     */
    public boolean isOwnerOrAdmin(Long produitId, Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject(); // ou jwt.getClaim("sub")

        // Tu dois récupérer l'utilisateur depuis la BDD par son email
        Utilsateur currentUser = userReop.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        return isAdmin(currentUser) || isOwner(currentUser, produit);
    }

    /**
     * Vérifie si l'utilisateur est propriétaire du produit
     */
    public boolean isOwner(Utilsateur user, Produit produit) {
        return produit.getProprietaire() != null &&
                produit.getProprietaire().getId().equals(user.getId());
    }

    /**
     * Vérifie si l'utilisateur est admin
     */
    public boolean isAdmin(Utilsateur user) {
        return user.getRole().name().equals("ADMIN");
    }
}