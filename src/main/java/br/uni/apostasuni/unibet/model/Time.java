package br.uni.apostasuni.unibet.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String nome;

    @OneToMany(mappedBy = "timeA")
    private List<Jogo> jogosA;

    @OneToMany(mappedBy = "timeB")
    private List<Jogo> jogosB;
}
