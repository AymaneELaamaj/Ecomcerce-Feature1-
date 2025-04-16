package com.example.Ecomerce.feature1.controller;

import com.example.Ecomerce.feature1.Model.Produit;
import com.example.Ecomerce.feature1.Service.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProduitController {
    public ProduitController(IProduitService iProduitService) {
        this.iProduitService = iProduitService;
    }

    @Autowired(required = true)
    private IProduitService iProduitService;
    @GetMapping("/allproduit")
    public List<Produit> getall(){
        return iProduitService.ProduitList();
    }
    @PostMapping("/saveproduit")
    public Produit saveone(@RequestBody Produit produit){
        return iProduitService.Saveproduit(produit);
    }
    @PutMapping("/updateproduit/{id}")
    public Produit updateone(@PathVariable Long id,@RequestBody Produit produit){
        return iProduitService.Updateproduit(id,produit);
    }
    @DeleteMapping("/deleteproduit/{id}")
    public void deleteone(@PathVariable Long id){
        iProduitService.Deleteproduit(id);
    }
}
