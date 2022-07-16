package com.esgi.identity.repositories;

import com.esgi.identity.data.UserIdentity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author mplayout
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<UserIdentity, Long> {
}
