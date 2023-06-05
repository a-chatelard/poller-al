package com.esgi.questions.domain.question;

import org.springframework.data.repository.CrudRepository;

public interface QuestionWriteRepository extends CrudRepository<Question, Long> {
}
