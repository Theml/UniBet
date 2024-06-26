package br.uni.apostasuni.unibet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.*;
//import jakarta.persistence.*

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
    private List<Aposta> minhasApostas;

    public void sacar(double valorAposta) {
        if(valorAposta > 0 && valorAposta <= saldo) {
            saldo -= valorAposta;
        }
    }

}
