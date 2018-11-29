package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.UserDevice;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
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

    @PostMapping("users/removeDevice")
    public User removeDevice(@RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().remove(device);
        device.getUsers().remove(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        return user;
    }

    @PostMapping("users/addDevice")
    public User addDevice(@RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().add(device);
        device.getUsers().add(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        return user;
    }
    //Admin addUser function
    @PostMapping("users/addUser")
    public List<User> addUser (@RequestBody User user){

        userRepository.save(user);

        return userRepository.findAll();
    }
    //Admin removeUser function
    @PostMapping("users/removeUser")
    public List<User> removeUser (@RequestBody Long userId){

        userRepository.deleteById(userId);

        return  userRepository.findAll();
    }
    //Admin updateUser function
    @PutMapping("users/{userId}")
    public User updateUser (@RequestBody User newUser, @PathVariable Long userId){
        User oldUser = userRepository.findById(userId).get();
        oldUser.setName(newUser.getName());
        oldUser.setSurName(newUser.getSurName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setRole(newUser.getRole());
        return userRepository.save(oldUser);
    }
}

