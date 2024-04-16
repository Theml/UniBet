package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dao.TimeDAO;
import br.uni.apostasuni.unibet.model.dto.JogoInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    JogoDAO jDAO;

    @Autowired
    TimeDAO tDAO;

    public List<Jogo> findAll() {
        return jDAO.findAll();
    }

    public Optional<Jogo> findById(int id) {
        return jDAO.findById(id);
    }

    public void createJogo(JogoInputDTO jogo) throws Exception {
        Optional<Jogo> game = jDAO.findById(jogo.getId());

        if (game.isPresent()) {
            throw new Exception("Este jogo já existe");
        }
        Optional<Time> timeA = tDAO.findById(jogo.getTimeAId());
        if (!timeA.isPresent()) {
            throw new Exception("Time não encontrado!");
        }
        Optional<Time> timeB = tDAO.findById(jogo.getTimeBId());
        if (!timeB.isPresent()) {
            throw new Exception("Time não encontrado!");
        }
        if (jogo.getJogoData() == null) {
            throw new Exception("Precisa passar o horário do jogo");
        }
        LocalDateTime dataJogo = jogo.getJogoData();
        List<Jogo> jogosNoMesmoHorario = jDAO.findByDataJogo(dataJogo);
        if(!jogosNoMesmoHorario.isEmpty()) {
            throw new Exception("Já existe um jogo nesse horário e data.");
        }


        Jogo j = new Jogo();
        j.setDataJogo(dataJogo);
        j.setTimeB(timeB.get());
        j.setTimeA(timeA.get());

        jDAO.save(j);
    }

    public void deleteJogo(int id) throws Exception{
        Optional<Jogo> j = jDAO.findById(id);
        if (j.isPresent()){
            jDAO.delete(j.get());
        } else {
            throw new Exception("Jogo inválido!!");
        }
    }

    public Jogo updateJogo(int id, Jogo jogo) throws Exception{
        Optional<Jogo> j = jDAO.findById(id);
        if (j.isEmpty()) {
            throw new Exception("Jogo inválido");
        }
        Optional<Time> timeA = tDAO.findById(jogo.getTimeA().getId());
        Optional<Time> timeB = tDAO.findById(jogo.getTimeB().getId());
        if (timeA == timeB) {
            throw new Exception("Os times precisão ser diferentes");
        } if(!timeA.isPresent() || !timeB.isPresent()) {
            throw new Exception("Os times não podem ser vazios");
        }
        LocalDateTime dataJogo = jogo.getDataJogo();
        List<Jogo> jogosNoMesmoHorario = jDAO.findByDataJogo(dataJogo);
        if(!jogosNoMesmoHorario.isEmpty()) {
            throw new Exception("Já existe um jogo nesse horário e data.");
        }

        Jogo alterandoJogo = j.get();
        alterandoJogo.setDataJogo(jogo.getDataJogo());
        alterandoJogo.setTimeA(jogo.getTimeA());
        alterandoJogo.setTimeB(jogo.getTimeB());

        return jDAO.save(alterandoJogo);
    }
}