package br.uni.apostasuni.unibet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nome;

    @OneToMany(mappedBy = "timeA")
    private ArrayList<Jogo> jogosA;

    @OneToMany(mappedBy = "timeB")
    private ArrayList<Jogo> jogosB;
}
