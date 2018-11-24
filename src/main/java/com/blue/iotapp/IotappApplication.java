package com.blue.iotapp;

import com.blue.iotapp.model.Role;
import com.blue.iotapp.model.Room;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IotappApplication {


	public static void main(String[] args) {
		SpringApplication.run(IotappApplication.class, args);

	}

		@Bean
		ApplicationRunner init(UserRepository userRepository) {
		return args -> {
			String[] usernames = {"Kwstas", "Makis", "Takis", "Lakis", "Unluckis", "Marika", "Nteni", "Nineta", "KwstasNtina"};
			String[] rooms = {"Bedroom", "Bathroom", "Kitchen", "Living Room"};
			String[] devices = {"Kwstas", "Makis", "Takis", "Lakis", "Unluckis", "Marika", "Nteni", "Nineta", "KwstasNtina"};
			for(String i: usernames) {
				User user = new User(i, "kwstas", "kwstAS@KWSTAS", "kwstasasasas", Role.USER);
				userRepository.save(user);
			}
			for(String room: rooms){
				Room rooM = new Room(room);
			}
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
