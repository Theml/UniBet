package br.uni.apostasuni.unibet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoInputDTO {

    private int id;
    private int timeAId, timeBId;

    private LocalDateTime jogoData;

}
