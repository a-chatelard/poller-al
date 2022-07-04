package com.esgi.identity.repositories;

import com.esgi.identity.data.UserIdentity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author mplayout
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<UserIdentity, Long> {


    UserIdentity findById(@Param("id") long id);

    List<UserIdentity> findAll();

    //void create(@Param("login") String login, @Param("lastName") String lastName);

}
