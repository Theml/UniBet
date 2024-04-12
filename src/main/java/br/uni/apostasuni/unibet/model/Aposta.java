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

    private  double valorAposta;

    @ManyToOne
    private Usuario user;

    @ManyToOne
    private Jogo jogo;

    private ETipoResultado aposta;
}
