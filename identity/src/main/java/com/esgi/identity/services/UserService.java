package com.esgi.identity.services;

import com.esgi.identity.data.UserIdentity;
import com.esgi.identity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<UserIdentity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserIdentity getUser(long userId) {


        return userRepository.findById(userId);

    }

    public void createUser(String login, String lastName) {
        UserIdentity userIdentity = new UserIdentity(login, lastName);
        userRepository.save(userIdentity);
    }

    boolean isUserExist(long userId) {
        return getUser(userId) != null;
    }
}
