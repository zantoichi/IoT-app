package com.blue.iotapp;

import com.blue.iotapp.model.*;
import com.blue.iotapp.repository.DeviceTypeRepository;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoomRepository;
import com.blue.iotapp.repository.UserRepository;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class IotappApplication {


	public static void main(String[] args) {
		SpringApplication.run(IotappApplication.class, args);

	}
	/* Overriding the main method to execute the below @Beans upon
	starting the Application in order to populate the database with mock data. */

	//This @Bean populates the database with mock data by creating objects.
	@Bean
	ApplicationRunner populateData(DeviceRepository deviceRepository,
									  RoomRepository roomRepository,
									  DeviceTypeRepository deviceTypeRepository,
								   UserRepository userRepository) {
		return args -> {
			String[] usernames = {"Kwstas", "Makis", "Takis", "Lakis", "Marika", "Nteni", "Nineta", "KwstasNtina"};
			for(String i: usernames) {
				User user = new User(i, "kwstas", "kwstAS@KWSTAS", "kwstasasasas", Role.USER);
				userRepository.save(user);
			}
			String[] deviceTypes = {"Air-condition", "Refrigerator", "Toaster", "Coffee Maker", "Fan"};
			for(String i: deviceTypes) {
				DeviceType deviceType = new DeviceType(i);
				deviceTypeRepository.save(deviceType);
			}

			String[] rooms = {"Bedroom", "Bathroom", "Kitchen", "Living Room", "Hallway"};
			for(String i: rooms) {
				Room room = new Room(i);
				roomRepository.save(room);
			}
			String[] devices = {"Fujitsu Air-condition", "Refrigerator - 1", "Toaster -1", "Coffee Maker - 1", "Fan -1"};
			for(String i: devices) {
				Hibernate.initialize(roomRepository.findByName("Kitchen"));
				Hibernate.initialize(deviceTypeRepository.findByName("Toaster"));
				Device device = new Device(i,deviceTypeRepository.findByName("Toaster"),roomRepository.findByName("Kitchen"));
				deviceRepository.save(device);
			}
			deviceRepository.findAll().forEach(System.out::println);
		};
	}
}