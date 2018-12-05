package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DeviceController {
    private DeviceRepository deviceRepository;
    private UserRepository userRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    // GET a list of all devices.
    @GetMapping("/devices")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    //Find device by ID.
    @GetMapping("/devices/{deviceId}")
    public Device findDevice (@PathVariable Long deviceId) {
        return deviceRepository.findById(deviceId).get();
    }

    // DELETE a device from DB
    @DeleteMapping("devices/deleteDevice/{deviceId}")
    public List<User> deleteDevice(@PathVariable ("deviceId") Long deviceId) {


        Device device = deviceRepository.findById(deviceId).get();

        List<User> users = userRepository.findAll();
        users.stream().map(User::getId).collect(Collectors.toList());

        for (User user: users){
            user.getDevices().remove(device);
            device.getUsers().remove(user);
        }

        deviceRepository.deleteById(deviceId);

        return  userRepository.findAll();
    }

    //Create new device through JSON.
    @PostMapping("/devices/newDevice")
    public List<Device> newDevice(@Valid @RequestBody Device device) {
        deviceRepository.save(device);
        return deviceRepository.findAll();
    }

    // Change the VALUE property of a device.
    @GetMapping("devices/{deviceId}/{changeValue}")
    public Device changeDeviceValue(@PathVariable("deviceId") Long deviceId, @PathVariable("changeValue") int changeValue) {
        Device device = deviceRepository.findById(deviceId).get();
        device.setValue(changeValue);
        deviceRepository.save(device);
        return device;
    }
    // GET the value property of a device.
    @GetMapping("devices/{deviceId}/getValue")
    public int getDeviceValue(@PathVariable("deviceId") Long deviceId) {
        Device device = deviceRepository.findById(deviceId).get();
        return device.getValue();
    }

    // GET the status of a Device.
    @GetMapping("/devices/{status}")
    public boolean getDevice(@PathVariable Long status) {
        Device device = deviceRepository.findById(status).get();
        if (device.getStatus() == false) {
            device.setStatus(true);
        } else {
            device.setStatus(false);
        }
        return device.getStatus();
    }

    //UPDATE a device name.
    @PutMapping("devices/updateDevice/{deviceId}")
    public Device updateDevice (@Valid @RequestBody User newDevice, @PathVariable Long deviceId){
        Device oldDevice = deviceRepository.findById(deviceId).get();
        oldDevice.setName(newDevice.getName());

        return deviceRepository.save(oldDevice);
    }
}
