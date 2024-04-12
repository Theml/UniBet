package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Aposta;
import br.uni.apostasuni.unibet.model.ETipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApostaDAO extends JpaRepository<Aposta, Integer> {

    public List<Aposta> findByUserIdAndJogoResultado(int id, ETipoResultado tipo);

    public Integer countByUserId(Integer id);

//    @Query("select count(a) from Aposta a where a.jogador.id = :id")
//    public Integer apostasDoUsuario(Integer id);
}
