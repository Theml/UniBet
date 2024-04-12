package br.uni.apostasuni.unibet.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoViewDTO {
    public String nome;

    private double valorGanho;
}
