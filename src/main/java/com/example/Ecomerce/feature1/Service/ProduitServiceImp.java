package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Component
@Service
@Transactional
public class ProduitServiceImp implements IProduitService {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired ProduitSecurityService produitSecurityService;
    @Override
    public Produit Saveproduit(Produit produit, Authentication authentication) {
        return produitRepository.save(produit);
    }

    @Override
    public void Deleteproduit(Long id,Authentication authentication) {
        if (!produitSecurityService.isOwnerOrAdmin(id, authentication)) {
            throw new RuntimeException("Vous n'avez pas les droits pour supprimer ce produit");
        }
        produitRepository.deleteById(id);
    }

    @Override
    public Produit Updateproduit(Long id, Produit produit,Authentication authentication) {
        if (!produitSecurityService.isOwnerOrAdmin(id, authentication)) {
            throw new RuntimeException("Vous n'avez pas les droits pour modifier ce produit");
        }
        Optional<Produit> produitOptional = produitRepository.findById(id);

        if (produitOptional.isPresent()) {
            Produit existingProduit = produitOptional.get();

            // Mise à jour des champs uniquement si ils ne sont pas null dans l'objet produit reçu
            if (produit.getName() != null) {
                existingProduit.setName(produit.getName());
            }
            if (produit.getDescription() != null) {
                existingProduit.setDescription(produit.getDescription());
            }
            if (produit.getPrice() != null) {
                existingProduit.setPrice(produit.getPrice());
            }
            if (produit.getStock() != 0) {
                existingProduit.setStock(produit.getStock());
            }



            return produitRepository.save(existingProduit);
        } else {
            throw new ResourceNotFoundException("Produit non trouvé avec l'id: " + id);
        }
    }

    @Override
    public List<Produit> ProduitList() {
        return produitRepository.findAll();
    }

}
