package br.uni.apostasuni.unibet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aposta {

    private int id;

    private Jogo jogo;

    private Usuario jogador;

    private  double valorAposta;

    private ETipoResultado aposta;
}
