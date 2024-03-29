package br.uni.apostasuni.unibet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private Jogo jogo;

    @ManyToOne
    private Usuario jogador;

    private  double valorAposta;

    private ETipoResultado aposta;
}
