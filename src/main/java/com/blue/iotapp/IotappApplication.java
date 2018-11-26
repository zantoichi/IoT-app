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


@SpringBootApplication
public class IotappApplication {


	public static void main(String[] args) {
		SpringApplication.run(IotappApplication.class, args);

	}

	@Bean
	ApplicationRunner populateUsers(UserRepository userRepository) {
		return args -> {
			String[] usernames = {"Kwstas", "Makis", "Takis", "Lakis", "Marika", "Nteni", "Nineta", "KwstasNtina"};
			for(String i: usernames) {
				User user = new User(i, "kwstas", "kwstAS@KWSTAS", "kwstasasasas", Role.USER);
				userRepository.save(user);
			}
		};
	}
	@Bean
	ApplicationRunner populateRooms(RoomRepository roomRepository) {
		return args -> {
			String[] rooms = {"Bedroom", "Bathroom", "Kitchen", "Living Room", "Hallway"};
			for(String i: rooms) {
				Room room = new Room(i);
				roomRepository.save(room);
			}
		};
	}
	@Bean
	ApplicationRunner populateDeviceTypes(DeviceTypeRepository deviceTypeRepository) {
		return args -> {
			String[] deviceTypes = {"Air-condition", "Refrigerator", "Toaster", "Coffee Maker", "Fan"};
			for(String i: deviceTypes) {
				DeviceType deviceType = new DeviceType(i);
				deviceTypeRepository.save(deviceType);
			}
		};
	}
//	@Bean
//	ApplicationRunner populateDevices(DeviceRepository deviceRepository) {
//		return args -> {
//			String[] deviceTypes = {"Air-condition", "Refrigerator", "Toaster", "Coffee Maker", "Fan"};
//			for(String i: deviceTypes) {
//				Device device = new Device(i);
//				deviceRepository.save(Device);
//			}
//			deviceRepository.findAll().forEach(System.out::println);
//		};
//	}
}