package br.uni.apostasuni.unibet.service;

import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    JogoDAO jDAO;

    public List<Jogo> findAll() {
        return jDAO.findAll();
    }
}