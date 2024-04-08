package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeService timeService;

    @PostMapping("")
    public ResponseEntity<?> saveTime(@RequestBody (required = true)Time time) {
        try {
            Time timeResp = timeService.verifySave(time);
            return ResponseEntity.status(HttpStatus.CREATED).body(timeResp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTime(@PathVariable (required = true) int id){
        try {
            timeService.removeTime(id);
            return ResponseEntity.ok("Time apagado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getTime() {

        return ResponseEntity.ok(timeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTimeByNome(@PathVariable (required = true) int id) {
        return ResponseEntity.ok(timeService.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeTime(@PathVariable(required = true) int id,
                                        @RequestBody Time time) {
        try {
            Time t = timeService.updateTime(id, time);
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}