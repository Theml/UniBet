package br.uni.apostasuni.unibet.controller;

import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    UsuarioService userService;

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable (required = true) int id) throws Exception {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody (required = true)Usuario usuario){
        try {
            Usuario userResp = userService.verifySave(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResp);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable (required = true) int id,
                                        @RequestBody Usuario usuario){
        try {
            Usuario user = userService.updateUser(id, usuario);
            return ResponseEntity.ok(user);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUser(@PathVariable (required = true) int id){
        try {
            userService.removeUser(id);
            return ResponseEntity.ok("Usuário removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}