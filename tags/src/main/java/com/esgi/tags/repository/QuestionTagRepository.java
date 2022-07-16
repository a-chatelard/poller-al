package com.esgi.tags.repository;

import com.esgi.tags.data.QuestionTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "questionTag", path = "questionTag")
public interface QuestionTagRepository extends PagingAndSortingRepository<QuestionTag, Long> {
    Optional<QuestionTag> findByQuestionIdAndTagLabel(Long questionId, String label);

    Page<QuestionTag> findAllByQuestionId(Long questionId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM QuestionTag qt WHERE qt.questionId = ?1")
    Integer deleteByQuestionId(Long questionId);
}
