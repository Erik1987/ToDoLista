package com.example.ToDoList;

import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ToDoList.domain.Lista;
import com.example.ToDoList.domain.ListaRepository;
import com.example.ToDoList.domain.Tekija;
import com.example.ToDoList.domain.TekijaRepository;
import com.example.ToDoList.domain.User;
import com.example.ToDoList.domain.UserRepository;


//git add .
//git commit -m "adding more junk"
//git push -u origin master

@SpringBootApplication
public class ToDoListApplication {

	private static final Logger log = LoggerFactory.getLogger(ToDoListApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}
	@Bean
	public CommandLineRunner ToDoListRunner(TekijaRepository trepository, ListaRepository lrepository,
			UserRepository usrepository) {
		return (args) -> {
			log.info("test");

			Date date = new Date(2019, 10, 25);
			lrepository.save(new Lista(date, "Muista pestä pyykit kello 1200"));
			lrepository.save(new Lista(date, "Muista tehdä kotitehtävät kello 1600"));
			trepository.save(new Tekija("Matti", "Meikäläinen", "Meikäläisenkatu 1", "Helsinki", "matti.meikalainen@gmail.com", lrepository.findByTeksti("Muista pestä pyykit kello 1200").get(0)));
			trepository.save(new Tekija("Malli", "Mallinen", "Mallikatu 1", "Helsinki", "malli.mallinen@gmail.com", lrepository.findByTeksti("Muista tehdä kotitehtävät kello 1600").get(0)));

					

			// Luodaan uusia käyttäjiä admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			usrepository.save(user1);
			usrepository.save(user2);
			

			// log.info("fetch all books");
			// for (Book book : brepository.findAll()) {
			// log.info(book.toString());
			// }

		};
	}

}

