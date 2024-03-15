package br.uni.apostasuni.unibet.controller;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/time")
    public String saveDesenvolvedores(@RequestBody String nome){
        if (nome != null && !nome.isBlank() && !nome.isEmpty()) {
            desenvolvedores.add( nome );
            return "Nome salvo com sucesso!";
        }
        return "Erro ao salvar o nome";
    }

    @DeleteMapping("/time/{id}")
    public  String deleteDesenvolvedor(@PathVariable int id) {
        if( id >= 1 && id <= desenvolvedores.size()) {
            desenvolvedores.remove(id - 1);
            return "Desenvolvedor apagado com sucesso!";
        }
        return "Erro ao apagar o desenvolvedor reveja o indice";
    }

    @PutMapping("/time/{id}")
    public String editDesenvolvedor(@PathVariable int id,
                                    @RequestBody String novoNome) {
        if(id >= 1 && id <= desenvolvedores.size()) {
            desenvolvedores.set(id - 1, novoNome);
            return "Alteração feita com sucesso";
        }
        return "Erro ao alterar o desenvolvedor";
    }

    @GetMapping("/time/search")
    public ArrayList<String> searchDesenvolvedor(@RequestParam(required = true) String nome,
                                                 @RequestHeader(required = true) String token) {

        ArrayList<String> search = new ArrayList<>();
        //return (ArrayList<String>) desenvolvedores.stream().filter(str -> str.contains(nome)).toList();
        if (token.equals("123")) {
            for (String str : desenvolvedores) {
                if (str.contains(nome)) {
                    search.add(str);
                }
            }
            return search;
        } else {
            search.add("Token incorreto!!!");
            return search;
        }
    }
}
