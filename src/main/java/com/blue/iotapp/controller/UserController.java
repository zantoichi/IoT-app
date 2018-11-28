package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public UserController(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) throws UserPrincipalNotFoundException {
        if (!userRepository.findById(id).isPresent()) {
            throw new UserPrincipalNotFoundException("id-" + id);
        }
        return userRepository.findById(id).get();
    }

    @PostMapping("users/addDevice")
    //TODO: FIX THIS
    public User addDevice(@RequestParam Long userId, @RequestParam Long deviceId) {
        User user = userRepository.findById(userId).get();
        Device device = deviceRepository.findById(deviceId).get();

        user.getDevices().add(device);
        device.getUsers().add(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        return user;
    }
}
