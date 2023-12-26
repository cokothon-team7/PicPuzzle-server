package com.example.cokothon.repository;

import com.example.cokothon.domain.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PuzzleRepository extends JpaRepository<Puzzle, Long> {

    @Override
    Puzzle save(Puzzle puzzle);

    @Override
    Optional<Puzzle> findById(Long puzzleId);

}
