package br.uni.apostasuni.unibet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private int id;
    private String nome, login, senha, email;
    private double saldo;

    private ArrayList<Aposta> minhasApostas;

}
