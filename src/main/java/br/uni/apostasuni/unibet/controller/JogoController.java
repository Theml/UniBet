package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.dto.JogoInputDTO;
import br.uni.apostasuni.unibet.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    JogoService jogoService;

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getJogos() {
        return ResponseEntity.ok(jogoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getJogoById(@PathVariable (required = true) int id) {
        return ResponseEntity.ok(jogoService.findById(id));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createJogo(@RequestBody JogoInputDTO jogo) {
        try {
            jogoService.createJogo(jogo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteJogo(@PathVariable (required = true) int id) {
        try {
            jogoService.deleteJogo(id);
            return ResponseEntity.ok("Jogo removido com sucesso!!!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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