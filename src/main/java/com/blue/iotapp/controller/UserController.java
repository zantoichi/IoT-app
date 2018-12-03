package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.UserDevice;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;
    private DeviceRepository deviceRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, DeviceRepository deviceRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // GET a list of all users.
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID.
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id)  {

        return userRepository.findById(id).get();
    }

    // DELETE a device ASSIGNED to a user by JSON(userID, deviceID)
    @PostMapping("users/removeDevice")
    public User removeDevice(@Valid @RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().remove(device);
        device.getUsers().remove(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        //log.info("device:" + device);
        //log.info("user:" + user);

        return user;
    }

    // ASSIGN a device to a user by JSON(userID, deviceID)
    @PostMapping("users/addDevice")
    public User addDevice(@Valid @RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().add(device);
        device.getUsers().add(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        log.info("device:" + device);
        log.info("user:" + user);

        return user;
    }

    // CREATE a new user by JSON.
    @PostMapping("users/adduser")
    public List<User> addUser (@Valid @RequestBody User user){

        String password = user.getPassword();
        String encryptPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptPassword);
        userRepository.save(user);
        return userRepository.findAll();
    }

    // DELETE a user by ID
    @PostMapping("users/removeUser/{userID}")
    public List<User> removeUser (@Valid @RequestParam("id") Long id){

        userRepository.deleteById(id);
       // log.info("userId:" + userId);

        return  userRepository.findAll();
    }

    // UPDATE a user by ID and JSON with the updated user.
    @PutMapping("users/{userId}")
    public User updateUser (@Valid @RequestBody User newUser, @PathVariable Long userId){
        User oldUser = userRepository.findById(userId).get();
        oldUser.setName(newUser.getName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setRole(newUser.getRole());

        //issues with log.info("Users:" + oldUser);

        return userRepository.save(oldUser);
    }
}

