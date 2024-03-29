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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nome, login, senha, email;

    private double saldo;
    private boolean ehAdmin;

    @OneToMany
    @JoinColumn(name = "id_minhas_apostas")
    private ArrayList<Aposta> minhasApostas;

}
