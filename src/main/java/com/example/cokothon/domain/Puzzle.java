package com.example.cokothon.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "puzzles")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Puzzle {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puzzle_id")
    private Long id;

    @ManyToOne
    private User user;

    private String hint;
    private String category;

    private String imagePath;

    @Column(name = "puzzle_col")
    private Long col;
    @Column(name = "puzzle_row")
    private Long row;
}
