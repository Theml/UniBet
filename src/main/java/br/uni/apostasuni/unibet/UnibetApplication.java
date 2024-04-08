package br.uni.apostasuni.unibet;

import br.uni.apostasuni.unibet.model.ETipoResultado;
import br.uni.apostasuni.unibet.model.Jogo;
import br.uni.apostasuni.unibet.model.Time;
import br.uni.apostasuni.unibet.model.Usuario;
import br.uni.apostasuni.unibet.model.dao.ApostaDAO;
import br.uni.apostasuni.unibet.model.dao.JogoDAO;
import br.uni.apostasuni.unibet.model.dao.TimeDAO;
import br.uni.apostasuni.unibet.model.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class UnibetApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UnibetApplication.class, args);
	}


	@Autowired
	TimeDAO dTime;

	@Autowired
	UsuarioDAO dUser;

	@Autowired
	JogoDAO jDAO;

	@Autowired
	ApostaDAO aDAO;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("");

		Time time = new Time(1,"Flamengo", null, null);
		Time time1 = new Time(2, "Vasco", null, null);

		dTime.save(time);
		dTime.save(time1);

		Usuario u = new Usuario(1, "Luis", "Lu", "123", "theml@gmail.com", 0, true, null);

		Usuario u1 = new Usuario(2, "Lua", "Lua", "123", "lua@gmail.com", 10000, false, null);

		dUser.save(u);
		dUser.save(u1);

		Jogo j = new Jogo(1, LocalDateTime.of(2024, 04, 05, 15, 30),
				0.8, 0.2, 0.1, time, time1,
				0, 0, ETipoResultado.AGUARDANDO);

		Jogo j1 = new Jogo(1, LocalDateTime.now(),
				1.8, 0.2, 0.1, time, time1,
				0, 0, ETipoResultado.AGUARDANDO);

		jDAO.save(j);
		jDAO.save(j1);
	}
}