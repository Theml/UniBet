package br.uni.apostasuni.unibet;

import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.TimeDTO;
import br.uni.apostasuni.unibet.model.dao.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnibetApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(UnibetApplication.class, args);
	}

	@Autowired
	TimeDTO dTime;
	@Autowired
	UsuarioDTO dUser;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("########### Inciando carregamento dos dados ############");

		Time time = new Time(1,"Flamengo", null,null);
		Time time1 = new Time(2,"Vasco", null,null);

		dTime.save(time);
		dTime.save(time1);

		Usuario user = new Usuario
				(1,"Luis","Lu","123",
						"Lu@gmail.com",0,true,null);

		Usuario user1 = new Usuario
				(2,"Lua","Lua","123",
						"Lua@gmail.com",0,false,null);

		dUser.save(user);
		dUser.save(user1);
	}
}
