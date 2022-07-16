package com.esgi.tags.repository;

import com.esgi.tags.data.UserTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "userTag", path = "userTag")
public interface UserTagRepository extends PagingAndSortingRepository<UserTag, Long> {
    Optional<UserTag> findByUserIdAndTagLabel(Long userId, String label);

    Page<UserTag> findAllByUserId(Long userId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserTag ut WHERE ut.userId = ?1")
    Integer deleteByUserId(Long userId);
}
