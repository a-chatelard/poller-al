package com.esgi.tags.repository;

import com.esgi.tags.data.UserTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userTag", path = "userTag")
public interface UserTagRepository extends PagingAndSortingRepository<UserTag, Long> {
    long deleteByUserId(Long userId);
}
