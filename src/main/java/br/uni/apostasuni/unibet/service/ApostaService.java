package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.Aposta;
import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.ApostaDAO;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import br.uni.apostasuni.unibet.model.dto.ApostaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ApostaService {

    @Autowired
    ApostaDAO aDAO;

    @Autowired
    UsuarioDAO uDAO;

    @Autowired
    JogoDAO jDAO;

    public void createAposta(ApostaInputDTO aposta) throws Exception {

        //verificar se o apostador existe.
        Optional<Usuario> user = uDAO.findById(aposta.getIdJogador());
        if( !user.isPresent() ) {
            throw new Exception("Usuário não encontrado!");
        }
        // verificar se possui saldo
        if(user.get().getSaldo() <= aposta.getValorAposta()){
            throw new Exception("Saldo insuficiente. Deposite mais!!");
        }
        //verificar se o jogo existe.
        Optional<Jogo> jogo = jDAO.findById(aposta.getIdJogo());
        if (!jogo.isPresent()) {
            throw new Exception("Jogo não encontrado");
        }
        if (jogo.get().getDataJogo().isBefore(LocalDateTime.now().minusMinutes(30))){
            throw new Exception("Apostas encerradas!! Perdeu a aposta bixo");
        }

        Aposta apo = new Aposta(1, aposta.getValorAposta(), user.get(),
                jogo.get(), aposta.getResultado());

        aDAO.save(apo);
        user.get().sacar(aposta.getValorAposta());

        uDAO.save(user.get());
    }
}
