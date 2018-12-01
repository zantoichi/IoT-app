package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.UserDevice;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User removeDevice(@Valid @RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().remove(device);
        device.getUsers().remove(user);

        deviceRepository.save(device);
        user = userRepository.save(user);
        log.info("device:" + device);
        log.info("user:" + user);

        return user;
    }

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
    //Admin addUser function
    @PostMapping("users/addUser")
    public List<User> addUser (@Valid @RequestBody User user){

        userRepository.save(user);
        log.info("user:" + user);


        return userRepository.findAll();
    }
    //Admin removeUser function
    @PostMapping("users/removeUser")
    public List<User> removeUser (@Valid @RequestParam ("id") Long id){

        userRepository.deleteById(id);
       // log.info("userId:" + userId);

        return  userRepository.findAll();
    }
    //Admin updateUser function
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

