package com.example.cokothon.repository;

import com.example.cokothon.domain.Quiz;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuizRepository {

    private final EntityManager em;

    public void save(Quiz quiz) {
        em.persist(quiz);
    }

    public Optional<Quiz> findOne(Long id) {
        return Optional.of(em.find(Quiz.class, id));
    }
}
