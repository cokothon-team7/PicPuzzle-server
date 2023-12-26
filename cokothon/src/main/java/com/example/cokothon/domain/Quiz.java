package com.example.cokothon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quizs")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id @GeneratedValue
    @Column(name = "quiz_id")
    private Long id;

    private String title;
    private String content;
    private String image;

    @ElementCollection
    private List<String> choices;

    private Integer answer;
}
