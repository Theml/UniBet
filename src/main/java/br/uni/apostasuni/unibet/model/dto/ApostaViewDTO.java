package br.uni.apostasuni.unibet.model.dto;

import br.uni.apostasuni.unibet.model.ETipoResultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApostaViewDTO {

    private int id;
    private double valorAposta;
    private int idJogo;
    private int idJogador;
    private String nomeJogador;
    private LocalDateTime dataJogo;
    private int idTime1;
    private String Time1;
    private int idTime2;
    private String Time2;
    private ETipoResultado resultadoJogo;
    private ETipoResultado resultadoApostado;
    private boolean acertou;
}
