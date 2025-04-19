package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Utilsateur;

import java.util.List;

public interface IUserService {
    public List<Utilsateur> USERS_LIST();
    public void removeUsers(Long id) ;

    public Utilsateur addUsers(Utilsateur users);
}
