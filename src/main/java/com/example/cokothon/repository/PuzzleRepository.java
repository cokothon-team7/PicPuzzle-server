package com.example.cokothon.repository;

import com.example.cokothon.domain.Puzzle;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PuzzleRepository {

    private final EntityManager em;

    public void save(Puzzle puzzle) {
        em.persist(puzzle);
    }

    public Optional<Puzzle> findById(Long id) {
        return Optional.ofNullable(em.find(Puzzle.class, id));
    }

    public List<Puzzle> findAllByUser(User user) {
        return em.createQuery("select p from Puzzle p where p.user.email = :email", Puzzle.class)
                .setParameter("email", user.getUsername())
                .getResultList();
    }
}
