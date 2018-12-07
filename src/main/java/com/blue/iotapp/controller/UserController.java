package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.Role;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.AdminUser;
import com.blue.iotapp.payload.UserDevice;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoleRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository;
    private DeviceRepository deviceRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository userRepository,
                          DeviceRepository deviceRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder,
                          RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
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

        //deviceRepository.save(device);
        user = userRepository.save(user);

        return user;
    }

    // ASSIGN a device to a user by JSON(userID, deviceID)
    @PostMapping("users/addDevice")
    public User addDevice(@Valid @RequestBody UserDevice userDevice) {
        User user = userRepository.findById(userDevice.getUserId()).get();
        Device device = deviceRepository.findById(userDevice.getDeviceId()).get();

        user.getDevices().add(device);
        device.getUsers().add(user);

        //deviceRepository.save(device);
        user = userRepository.save(user);

        return user;
    }

    // CREATE a new user by JSON.
    @PostMapping("users/addUser")
    public User addUser (@Valid @RequestBody AdminUser adminUser){

        User user = new User(adminUser.getFirstName(), adminUser.getLastName(),
                adminUser.getEmail(), adminUser.getPassword());
        String password = adminUser.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        if (adminUser.getRole() != null){
            Role userRole = roleRepository.findByName(adminUser.getRole()).get();
            user.setRoles(Collections.singleton(userRole));
        }

        return userRepository.save(user);
    }

    // DELETE a user by ID
    @GetMapping("users/deleteUser/{userId}")
    public List<User> removeUser (@Valid @PathVariable("userId") Long userId){

        userRepository.deleteById(userId);

        return  userRepository.findAll();
    }

    // UPDATE a user by ID and JSON with the updated user.
    @PutMapping("users/updateUser/{userId}")
    public User updateUser (@Valid @RequestBody AdminUser updatedUser, @PathVariable Long userId){

        User oldUser = userRepository.findById(userId).get();
        oldUser.setName(updatedUser.getFirstName());
        oldUser.setLastName(updatedUser.getLastName());
        oldUser.setEmail(updatedUser.getEmail());
        //TODO: append new Role to User
        Role userRole = roleRepository.findByName(updatedUser.getRole()).get();
        oldUser.getRoles().removeAll(oldUser.getRoles());
        oldUser.getRoles().add(userRole);
        return userRepository.save(oldUser);
    }

}

