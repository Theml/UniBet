package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {
}
