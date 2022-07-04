package com.esgi.tags.repository;

import com.esgi.tags.data.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tag", path = "tag")
public interface TagRepository extends PagingAndSortingRepository<Tag, String> {
}
