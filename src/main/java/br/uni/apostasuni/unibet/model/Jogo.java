package br.uni.apostasuni.unibet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    private  int idJogo;

    private double oddsVitoriaTimeA, oddsVitoriaTimeB, oddsEmpate;

    private LocalDateTime dataJogo;

    private  Time timeA, timeB;

    private int pontosTimeA, pontosTimeB;

    private ETipoResultado resultado;
}
