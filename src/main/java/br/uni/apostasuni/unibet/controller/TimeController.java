package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    TimeService timeService;

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveTime(@RequestBody (required = true)Time time) {
        try {
            Time timeResp = timeService.verifySave(time);
            return ResponseEntity.status(HttpStatus.CREATED).body(timeResp);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTime(@PathVariable (required = true) int id){
        try {
            timeService.removeTime(id);
            return ResponseEntity.ok("Time apagado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTime() {

        return ResponseEntity.ok(timeService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTimeByNome(@PathVariable (required = true) int id) {
        return ResponseEntity.ok(timeService.find(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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