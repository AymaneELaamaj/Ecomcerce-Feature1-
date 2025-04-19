package com.example.Ecomerce.feature1.Service;

import com.example.Ecomerce.feature1.Model.Utilsateur;
import com.example.Ecomerce.feature1.Repository.UserReop;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceimpl implements IUserService{
    private UserReop userRepo;

    public UserServiceimpl(UserReop userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<Utilsateur> USERS_LIST() {
        return userRepo.findAll();
    }

    @Override
    public void removeUsers(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Utilsateur addUsers(Utilsateur users) {
        return userRepo.save(users);
    }
}
