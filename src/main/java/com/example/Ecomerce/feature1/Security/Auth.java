package com.example.Ecomerce.feature1.Security;

import com.example.Ecomerce.feature1.DTO.RegisterRequest;
import com.example.Ecomerce.feature1.Eums.Role;
import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.UserReop;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@NoArgsConstructor
@RequestMapping("/auth")
public class Auth {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserReop userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public Auth(AuthenticationManager authenticationManager, UserReop userRepository, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    // Fichier: controller/AuthController.java
    @PostMapping("/register")
    public ResponseEntity<String> register( @RequestBody RegisterRequest request) {
        System.out.println("Recherche email: " + request.email());
        System.out.println("Recherche password: " + request.password());
        Optional<Utilsateur> existing = userRepository.findByEmail(request.email());
        System.out.println("Utilisateur existant: " + existing);

        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        // Créer et sauvegarder le nouvel utilisateur
        Utilsateur user = new Utilsateur();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);
        System.out.println("Mot de passe encodé: " + passwordEncoder.encode(request.password()));


        return ResponseEntity.ok("Utilisateur enregistré avec succès");
    }
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        System.out.println("Authenticating user: " + email);
        System.out.println("Password: " + password);
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        System.out.println("Authentication successful for: " + email);

        System.out.println("email: " + email);
        System.out.println("Password: " + password);
        Instant instant=Instant.now();
        String autorities = authenticate.getAuthorities().stream().map(ls -> ls.getAuthority()).collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(1, ChronoUnit.HOURS))
                .subject(email)
                .claim("scope",autorities)
                .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                jwtClaimsSet
        );
        String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("token",jwt);

    }
}
