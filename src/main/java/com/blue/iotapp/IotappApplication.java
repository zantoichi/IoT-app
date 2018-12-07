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
}