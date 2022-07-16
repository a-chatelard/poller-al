package com.esgi.identity.controllers;

import com.esgi.identity.data.UserIdentity;
import com.esgi.identity.data.services.UserService;
import com.esgi.identity.data.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RestController
public class UserController {

    /**
     * The user service.
     */
    @Autowired
    private UserService userService;

    /**
     * Gets all users .
     * @param pageable the paging parameters.
     * @return a list of Users.
     */
    @GetMapping("/users")
    public Page<UserIdentity> getAllUsers(final Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    /**
     * Gets a user of a user's id.
     *
     * @param userId the user id.
     * @return the User.
     */
    @GetMapping("/users/{userId}")
    public UserIdentity getUser(final @PathVariable(name = "userId") Long userId) throws ResourceNotFoundException {
        return userService.getUser(userId);
    }

    /**
     * Create a new user of a user's id.
     *
     * @param login    the first name of the user.
     * @param lastName the last name of the user.
     */
    @PostMapping("/users/add")
    public void createUser(final @RequestParam @Valid String login,
                           final @RequestParam @Valid String lastName) {
        userService.createUser(login, lastName);
    }

    /**
     * Return if a user exists with the concerned ID.
     *
     * @param userId the ID of the user.
     * @return true if the user exist, otherwise false.
     */
    @GetMapping("/users/{userId}/exists")
    public boolean doesUserExist(final @PathVariable(name = "userId") Long userId) {
        return userService.doesUserExist(userId);
    }

    /**
     * Delete a user and all its related data.
     * @param userId the user ID.
     */
    @DeleteMapping("/users/{userId}")
    public void deleteUser(final @PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
    }
}
