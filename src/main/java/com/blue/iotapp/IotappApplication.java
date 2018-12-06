package com.blue.iotapp;

import com.blue.iotapp.model.*;
import com.blue.iotapp.repository.*;
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
								   UserRepository userRepository,
								   RoleRepository roleRepository) {
		return args -> {
			String[] deviceTypes = {"Air-condition", "Refrigerator", "Toaster", "Coffee Maker", "Fan"};
			for (String i : deviceTypes) {
				DeviceType deviceType = new DeviceType(i);
				deviceTypeRepository.save(deviceType);
			}

			List<User> users = new ArrayList<>();
			List<Device> devices = new ArrayList<>();


			String[] emails = {"Kwstas@asd.com", "Makis@asd.com", "Takis@asd.com",
					"Lakis@asd.com", "Marika@asd.com", "Nteni@asd.com",
					"Nineta@asd.com", "KwstasNtina@asd.com"};
			Arrays.stream(emails).forEach(email -> users.add(new User("Kwstas",
					"kwstas", email, "kwstasasasas")));

			String[] deviceNames = {"Fujitsu Air-condition", "Refrigerator - 1",
					"Toaster -1", "Coffee Maker - 1", "Fan -1"};
			for (String deviceName : deviceNames) {
				Device device = new Device(deviceName, deviceTypeRepository.findByName("Toaster"), roomRepository.findByName("Kitchen"));
				devices.add(device);
			}
			//to mono pou kaneis auto einai populate, den ftiaxnei pinaka !!!
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

			Role user_role = new Role("ROLE_USER");
			Role admin_role = new Role("ROLE_ADMIN");
			roleRepository.save(user_role);
			roleRepository.save(admin_role);




//            room = roomRepository.findByName("Bathroom");
//            room.getDevices().addAll(devices);
//            roomRepository.save(room);
		};
	}
}