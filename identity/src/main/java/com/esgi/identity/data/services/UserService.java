package com.esgi.identity.data.services;

import com.esgi.identity.data.UserIdentity;
import com.esgi.identity.repositories.UserRepository;
import com.esgi.identity.data.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserService {

    /**
     * The user repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Get all the user with paging.
     * @param pageable the paging parameters.
     * @return a paged list of all the users.
     */
    public Page<UserIdentity> getAllUsers(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * Get a user using its ID.
     * @param userId the user ID.
     * @return the related user.
     * @throws ResourceNotFoundException user not found.
     */
    public UserIdentity getUser(final long userId) throws ResourceNotFoundException {
        Optional<UserIdentity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(UserIdentity.class, userId);
        }
        return user.get();
    }

    /**
     * Create a user.
     * @param login the user login.
     * @param lastName the user lastname.
     */
    public void createUser(final String login, final String lastName) {
        UserIdentity userIdentity = new UserIdentity(login, lastName);
        userRepository.save(userIdentity);
    }

    /**
     * Determine if a user exists using its ID.
     * @param userId the user ID.
     * @return true if the user exists in the database, false overwise.
     */
    public boolean doesUserExist(final long userId) {
        return userRepository.existsById(userId);
    }

    /**
     * Delete a user and all the related data in the other micro-services.
     * @param userId the user ID.
     */
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);

        final String deleteUserAnswersUri = "http://localhost:8082/questions/user/" + userId;
        final String deleteUserTagsUri = "http://localhost:8081/tags/user/" + userId;

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(deleteUserAnswersUri);
        restTemplate.delete(deleteUserTagsUri);
    }
}
