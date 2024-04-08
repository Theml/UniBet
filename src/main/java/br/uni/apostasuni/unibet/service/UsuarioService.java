package br.uni.apostasuni.unibet.service;


import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO uDAO;

    public Usuario verifySave(Usuario user) throws Exception {
        return  null;
    }

    public List<Usuario> findAllUser() {
        return uDAO.findAll();
    }
}