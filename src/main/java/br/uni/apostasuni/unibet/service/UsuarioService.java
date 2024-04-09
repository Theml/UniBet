package br.uni.apostasuni.unibet.service;


import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDAO uDAO;

    public Usuario verifySave(Usuario user) throws Exception {
        if (user.getNome().isEmpty() || user.getNome().isBlank()){
            throw new Exception("Nome de usuário não pode ser vazio!!");
        }
        Usuario u = uDAO.findByNome(user.getNome());
        if (u != null){
            throw new Exception("Nome de usuário já cadastrado!!");
        } else {
            return uDAO.save(user);
        }
    }

    public List<Usuario> findAllUser() {
        return uDAO.findAll();
    }

    public Usuario findById(int id) throws Exception {
        Optional<Usuario> user = uDAO.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("Usuario não encontrado.");
        }
    }

    public Usuario updateUser(int id, Usuario usuario) throws Exception{
        Optional<Usuario> user = uDAO.findById(id);
        if (user.isEmpty()) {
            throw new Exception("Usuário inválido!");
        }
        if (usuario.getNome().isEmpty() || usuario.getNome().isBlank()) {
            throw new Exception("Nome de usuário não pode ser vazio!!");
        }
        Usuario loginAlreadyExist = uDAO.findByLogin(usuario.getLogin());
        if ( loginAlreadyExist != null){
            throw new Exception("Nome " + loginAlreadyExist + " já está cadastrado");
        }
        Usuario userUpdate = user.get();
        userUpdate.setNome(usuario.getNome());
        userUpdate.setLogin(usuario.getLogin());
        return uDAO.save(userUpdate);
    }

    public void removeUser(int id) throws Exception {
        Optional<Usuario> u = uDAO.findById(id);
        if (u.isPresent()){
            uDAO.delete(u.get());
        } else {
            throw new Exception("Usuário inválido");
        }
    }
}