package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Aposta;
import br.uni.apostasuni.unibet.model.ETipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {

    public List<Aposta> findByJogador(String jogador);

    public List<Aposta> findByJogadorIdAndJogoResultado(int id, ETipoResultado tipo);
}
