package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDAO extends JpaRepository<Time, Integer> {

    Time findByNome(String nome);
}
