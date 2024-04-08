package br.uni.apostasuni.unibet.model.dto;

import br.uni.apostasuni.unibet.model.ETipoResultado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApostaInputDTO {

    private int idJogador, idJogo;

    private double valorAposta;
    private ETipoResultado resultado;
}
