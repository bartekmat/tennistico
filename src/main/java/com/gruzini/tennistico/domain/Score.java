package com.gruzini.tennistico.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "scores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany
    private List<Set> sets;

    @OneToOne
    private Player winner;

    @OneToOne
    private Player loser;

}