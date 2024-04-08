package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoDAO extends JpaRepository<Jogo, Integer> {
}
