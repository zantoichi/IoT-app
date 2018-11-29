package com.blue.iotapp;

import com.blue.iotapp.model.*;
import com.blue.iotapp.repository.DeviceTypeRepository;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoomRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class IotappApplication {


	public static void main(String[] args) {
		SpringApplication.run(IotappApplication.class, args);

	}
	/* Overriding the main method to execute the below @Bean upon
	starting the Application in order to populate the database with mock data. */

	//This @Bean populates the database with mock data by creating objects.
	@Bean
	ApplicationRunner populateData(DeviceRepository deviceRepository,
								   RoomRepository roomRepository,
								   DeviceTypeRepository deviceTypeRepository,
								   UserRepository userRepository) {
		return args -> {
			String[] deviceTypes = {"Air-condition", "Refrigerator", "Toaster", "Coffee Maker", "Fan"};
			for (String i : deviceTypes) {
				DeviceType deviceType = new DeviceType(i);
				deviceTypeRepository.save(deviceType);
			}

			List<User> users = new ArrayList<>();
			List<Device> devices = new ArrayList<>();


			String[] usernames = {"Kwstas", "Makis", "Takis", "Lakis", "Marika", "Nteni", "Nineta", "KwstasNtina"};
			Arrays.stream(usernames).forEach(username -> users.add(new User(username, "kwstas", "kwstAS@KWSTAS", "kwstasasasas", Role.USER)));

			String[] deviceNames = {"Fujitsu Air-condition", "Refrigerator - 1", "Toaster -1", "Coffee Maker - 1", "Fan -1"};
			for (String deviceName : deviceNames) {
				Device device = new Device(deviceName, deviceTypeRepository.findByName("Toaster"), roomRepository.findByName("Kitchen"));
				devices.add(device);
			}
			for (User user:users){
				for (Device device: devices){
					device.getUsers().add(user);
					user.getDevices().add(device);
				}
			}
			userRepository.saveAll(users);
			deviceRepository.saveAll(devices);


			String[] rooms = {"Bedroom", "Bathroom", "Kitchen", "Living Room", "Hallway"};
			Room room;
			for (String i : rooms) {
				room = new Room(i);
				roomRepository.save(room);
			}




//            room = roomRepository.findByName("Bathroom");
//            room.getDevices().addAll(devices);
//            roomRepository.save(room);
		};
	}
}