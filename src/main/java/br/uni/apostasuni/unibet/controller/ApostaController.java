package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.dto.ApostaInputDTO;
import br.uni.apostasuni.unibet.model.dto.ApostaViewDTO;
import br.uni.apostasuni.unibet.service.ApostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    @Autowired
    ApostaService apoServ;

    @PostMapping("")
    public ResponseEntity<?> createAposta(@RequestBody ApostaInputDTO aposta) {
        try {
            apoServ.createAposta( aposta );
            // Gerar status 201 afirmando que a aposta foi criada.
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarAposta(@PathVariable (required = true) Integer id) throws Exception {
        try {
            ApostaViewDTO a = apoServ.getAposta(id);
            return ResponseEntity.ok(a);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getApostaByUser(@PathVariable (required = true) Integer id) {
        return ResponseEntity.ok(apoServ.getApostaUsuario(id));
    }
}