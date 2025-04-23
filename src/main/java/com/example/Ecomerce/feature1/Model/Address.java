package com.example.Ecomerce.feature1.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street; // Rue

    private String city; // Ville

    private String postalCode; // Code postal

    private String country; // Pays

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilsateur user; // L'utilisateur auquel cette adresse appartient

    public Address() {
    }

    public Address(String street, String city, String postalCode, String country, Utilsateur user) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.user = user;
    }
}
