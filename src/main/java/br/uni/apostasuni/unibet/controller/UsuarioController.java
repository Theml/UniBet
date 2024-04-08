package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {

        return ResponseEntity.ok(userService.findAllUser());
    }
}