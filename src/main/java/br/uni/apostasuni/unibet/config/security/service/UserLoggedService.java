package br.uni.apostasuni.unibet.config.security.service;

import br.uni.apostasuni.unibet.config.security.model.UserLogged;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoggedService implements UserDetailsService {

    @Autowired
    UsuarioDAO uDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = uDAO.findByLogin(username);

        if(u == null) {
            throw new UsernameNotFoundException("Usuário ou Senha incorreta!!");
        } else {
            return new UserLogged(u);
        }

    }
}
