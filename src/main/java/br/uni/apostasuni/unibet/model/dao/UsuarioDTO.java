package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDTO extends JpaRepository<Usuario, Integer> {

}
