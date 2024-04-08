package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    JogoService jogoService;

    @GetMapping("")
    public ResponseEntity<?> getJogos() {
        return ResponseEntity.ok(jogoService.findAll());
    }
}