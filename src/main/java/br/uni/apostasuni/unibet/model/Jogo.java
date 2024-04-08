package br.uni.apostasuni.unibet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  int idJogo;

    private LocalDateTime dataJogo;
    private double oddsVitoriaTimeA, oddsVitoriaTimeB, oddsEmpate;

    @ManyToOne
    @JoinColumn(name = "id_time_A")
    private  Time timeA;

    @ManyToOne
    @JoinColumn(name = "id_time_B")
    private Time timeB;

    private int pontosTimeA, pontosTimeB;

    private ETipoResultado resultado;
}
