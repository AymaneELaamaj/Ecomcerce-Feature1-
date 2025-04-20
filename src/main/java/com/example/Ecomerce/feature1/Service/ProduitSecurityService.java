package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Eums.Role;
import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ProduitSecurityService {

    private final ProduitRepository produitRepository;

    public ProduitSecurityService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public boolean isOwnerOrAdmin(Long produitId, Authentication authentication) {
        Utilsateur currentUser = (Utilsateur) authentication.getPrincipal();

        // Si admin, autoriser directement
        if (currentUser.getRole() == Role.ADMIN) {
            return true;
        }

        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        // Vérifier si l'utilisateur est le propriétaire
        return produit.getProprietaire() != null &&
                produit.getProprietaire().getId().equals(currentUser.getId());
    }
}
