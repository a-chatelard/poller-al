package fr.gamedev.tags.repository;

import fr.gamedev.tags.data.Tag;
import fr.gamedev.tags.data.UserTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "userTag", path = "userTag")
public interface UserTagRepository extends PagingAndSortingRepository<UserTag, Long> {

    List<UserTag> findAllByUserId(@Param("userId") Long userId);

    Optional<UserTag> findByUserIdAndTagId(@Param("tagId") Long tagId, @Param("userId") Long userId);
}
