package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.dto.JogoInputDTO;
import br.uni.apostasuni.unibet.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    JogoService jogoService;

    @GetMapping("")
    public ResponseEntity<?> getJogos() {
        return ResponseEntity.ok(jogoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJogoById(@PathVariable (required = true) int id) {
        return ResponseEntity.ok(jogoService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createJogo(@RequestBody JogoInputDTO jogo) {
        try {
            jogoService.createJogo(jogo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogo(@PathVariable (required = true) int id) {
        try {
            jogoService.deleteJogo(id);
            return ResponseEntity.ok("Jogo removido com sucesso!!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJogo(@PathVariable (required = true) int id,
                                        @RequestBody Jogo jogo) {
        try {
            Jogo j = jogoService.updateJogo(id, jogo);
            return ResponseEntity.ok(j);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}