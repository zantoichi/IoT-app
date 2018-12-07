package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.Role;
import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.AdminCreateUser;
import com.blue.iotapp.payload.UserDevice;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoleRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
    public User addUser (@Valid @RequestBody AdminCreateUser adminCreateUser){

        User user = new User(adminCreateUser.getName(), adminCreateUser.getLastName(),
                adminCreateUser.getEmail(), adminCreateUser.getPassword());
        String password = adminCreateUser.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        if (adminCreateUser.getRole() != null){
            Role userRole = roleRepository.findByName(adminCreateUser.getRole()).get();
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
    @PutMapping("users/{userId}")
    public User updateUser (@Valid @RequestBody AdminCreateUser newUser, @PathVariable Long userId){

        User oldUser = userRepository.findById(userId).get();
        oldUser.setName(newUser.getName());
        oldUser.setLastName(newUser.getLastName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        if (newUser.getRole() != null){
            Role userRole = roleRepository.findByName(newUser.getRole()).get();
            oldUser.setRoles(Collections.singleton(userRole));
        }

        return userRepository.save(oldUser);
    }

}

