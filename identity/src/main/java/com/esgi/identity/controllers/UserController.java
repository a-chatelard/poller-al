package com.esgi.identity.controllers;

import com.esgi.identity.data.UserIdentity;
import com.esgi.identity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Gets all users .
     *
     * @return a list of Users.
     */
    @GetMapping("/users")
    public List<UserIdentity> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Gets a user of an user's id.
     *
     * @param userId the user id.
     * @return the User.
     */
    @GetMapping("/users/{userId}")
    public UserIdentity getUser(@RequestParam final long userId) {

        return userService.getUser(userId);

    }

    /**
     * Create a new user of an user's id.
     *
     * @param login    the first name of the user.
     * @param lastName the last name of the user.
     */
    @PostMapping("/users/add")
    public void createUser(@RequestParam
                                   @Valid String login, @RequestParam @Valid String lastName) {


        userService.createUser(login, lastName);
    }

    /**
     * Return if a user exists with the concerned ID.
     *
     * @param userId the ID of the user.
     * @return true if the user exist, otherwise false.
     */
    @GetMapping("/users/{userId}/exists")
    public boolean doesUserExist(@PathVariable(name = "userId") Long userId) {
        return userService.doesUserExist(userId);
    }
}
