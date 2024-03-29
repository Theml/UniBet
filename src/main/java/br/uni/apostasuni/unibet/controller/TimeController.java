package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.dao.TimeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeDTO tDto;
    @PostMapping("")
    public ResponseEntity<?> saveTime(@RequestBody (required = true) Time time) {

        if(time.getNome().isEmpty() || time.getNome().isBlank()) {
            return ResponseEntity.badRequest().body("Nome não pode ser vazio!!!");
        }
        Time t = tDto.findByNome(time.getNome());
        if (t == null) {
            time = tDto.save(time);
            return ResponseEntity.ok(time);
        }
        return ResponseEntity.ok("Não pode salvar!!");
    }

    @GetMapping("")
    public ResponseEntity<?> getTime() {
        return ResponseEntity.ok(tDto.findAll());
    }
}
