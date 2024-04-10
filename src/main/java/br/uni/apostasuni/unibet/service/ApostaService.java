package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.Aposta;
import br.uni.apostasuni.unibet.model.ETipoResultado;
import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.ApostaDAO;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import br.uni.apostasuni.unibet.model.dto.ApostaInputDTO;
import br.uni.apostasuni.unibet.model.dto.ApostaViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public ApostaViewDTO getAposta(Integer id) throws Exception{

        Optional<Aposta> aposta = aDAO.findById(id);

        if (aposta.isEmpty()) {
            throw new Exception("Aposta " + id + "não encontrada");
        }
        ApostaViewDTO ap = new ApostaViewDTO();
        ap.setId(aposta.get().getId());
        ap.setDataJogo(aposta.get().getJogo().getDataJogo());
        ap.setIdJogador(aposta.get().getJogador().getId());
        ap.setNomeJogador(aposta.get().getJogador().getNome());
        ap.setTime1( aposta.get().getJogo().getTimeA().getNome());
        ap.setIdTime1(aposta.get().getJogo().getTimeA().getId());
        ap.setTime2(aposta.get().getJogo().getTimeB().getNome());
        ap.setIdTime2(aposta.get().getJogo().getTimeB().getId());
        ap.setResultadoJogo(aposta.get().getJogo().getResultado());
        ap.setResultadoApostado(aposta.get().getAposta());
        ap.setValorAposta(aposta.get().getValorAposta());
        ap.setAcertou(aposta.get().getAposta() == aposta.get().getJogo().getResultado());

        return ap;
    }

    public List<ApostaViewDTO> getApostaUsuario(Integer id) {
       List<Aposta> lista = aDAO.findByJogadorIdAndJogoResultado(id, ETipoResultado.AGUARDANDO);

       List<ApostaViewDTO> listDTO = new ArrayList<>();
       for (Aposta a : lista) {
           ApostaViewDTO ap = new ApostaViewDTO();
           ap.setId(a.getId());
           ap.setDataJogo(a.getJogo().getDataJogo());
           ap.setIdJogador(a.getJogador().getId());
           ap.setNomeJogador(a.getJogador().getNome());
           ap.setTime1( a.getJogo().getTimeA().getNome());
           ap.setIdTime1(a.getJogo().getTimeA().getId());
           ap.setTime2(a.getJogo().getTimeB().getNome());
           ap.setIdTime2(a.getJogo().getTimeB().getId());
           ap.setResultadoJogo(a.getJogo().getResultado());
           ap.setResultadoApostado(a.getAposta());
           ap.setValorAposta(a.getValorAposta());
           ap.setAcertou(a.getAposta() == a.getJogo().getResultado());

           listDTO.add(ap);
       }
       return listDTO;
    }
}
