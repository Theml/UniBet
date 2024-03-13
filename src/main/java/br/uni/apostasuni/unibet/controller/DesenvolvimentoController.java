package br.uni.apostasuni.unibet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dev")
public class DesenvolvimentoController {

    static ArrayList<String> desenvolvedores;
    static {
        desenvolvedores = new ArrayList<>();
        desenvolvedores.add("M Lua");
        desenvolvedores.add("M Moon");
        desenvolvedores.add("M Moonshine");
    }

    @GetMapping("/time")
    public  String getDesenvTime() {
        String nome = "";
        for (String n: desenvolvedores) {
            nome += n+"; ";
        }
        return nome;
    }
    @GetMapping("/time/{posicao}")
    public String getDesenvTimeOne(@PathVariable int posicao){
        if(posicao >= 1 && posicao <= desenvolvedores.size()) {
            return desenvolvedores.get(posicao - 1);
        } else {
            return "Indice Incorreto";
        }
    }
}
