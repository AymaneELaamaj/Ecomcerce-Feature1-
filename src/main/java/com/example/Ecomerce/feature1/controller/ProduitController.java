package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.UserReop;
import com.example.Ecomerce.feature1.Service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Produit")
public class ProduitController {
    public ProduitController(IProduitService iProduitService,UserReop userReop) {
        this.iProduitService = iProduitService;
        this.userReop = userReop;

    }



    @Autowired(required = true)
    private IProduitService iProduitService;
    @Autowired
    private UserReop userReop;
    @GetMapping("/allproduit")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public List<Produit> getall(){
        return iProduitService.ProduitList();
    }
    @PostMapping("/saveproduit")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_VENDOR')")

    public Produit saveone(@RequestBody Produit produit, Authentication authentication){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String email = jwt.getSubject(); // ou jwt.getClaim("sub")

        // Tu dois récupérer l'utilisateur depuis la BDD par son email
        Utilsateur currentUser = userReop.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
        System.out.println("Utilisateur connecté : " + currentUser.getEmail());
        produit.setProprietaire(currentUser);
        return iProduitService.Saveproduit(produit,authentication);
    }
    @PutMapping("/updateproduit/{id}")
    @PreAuthorize("@produitSecurityService.isOwnerOrAdmin(#id, authentication)")
    public Produit updateone(@PathVariable Long id,@RequestBody Produit produit,Authentication authentication){

        return iProduitService.Updateproduit(id,produit,authentication);
    }
    @DeleteMapping("/deleteproduit/{id}")
    @PreAuthorize("@produitSecurityService.isOwnerOrAdmin(#id, authentication)")
    //Le # indique à Spring qu'il s'agit d'un paramètre de la méthode
    public void deleteone(@PathVariable Long id,Authentication authentication){
        iProduitService.Deleteproduit(id,authentication);
    }
}
