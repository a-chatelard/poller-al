package com.esgi.questions.controller.questionPosee;

import com.esgi.questions.application.questionPosee.repositories.QuestionPoseeReadRepository;
import com.esgi.questions.domain.questionPosee.QuestionPosee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/questions-posees")
@RestController
public class QuestionPoseeReadController {

    /**
     * Service gérant les requêtes concernant la lecture des questionPosee.
     */
    QuestionPoseeReadRepository repository;

    /**
     * Get a questionPosee.
     *
     * @param questionPoseeId the questionPosee Id.
     * @return the Id of the questionPosee
     */
    @GetMapping("/{questionPoseeId}")
    public QuestionPosee getQuestionPoseeById(Long questionPoseeId) {
        Optional<QuestionPosee> questionPosee = repository.getQuestionPoseeById(questionPoseeId);
        return questionPosee.orElse(null);
    }

    /**
     * Get all the questionPosee for an user.
     *
     * @param utilisateurId the user Id.
     * @return an array of qiestionPosee
     */
    @GetMapping("/utilisateur/{utilisateurId}")
    public List<QuestionPosee> getQuestionsPoseesByUtilisateurId(Long utilisateurId) {
        return repository.getQuestionsPoseesByUtilisateurId(utilisateurId);
    }
}



