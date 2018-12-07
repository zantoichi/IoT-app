package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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

    // Delete device by ID.
    @GetMapping("/devices/deleteDevice/{deviceId}")
    public void removeDevice(@PathVariable Long deviceId) {
        Device device = deviceRepository.findById(deviceId).get();

        Set<User> users = device.getUsers();

        for (Iterator<User> itUser = users.iterator(); itUser.hasNext();) {
            User user= itUser.next();
            user.getDevices().remove(device);

        }
        userRepository.saveAll(users);
        deviceRepository.deleteById(deviceId);

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
    @GetMapping("devices/getValue/{deviceId}/")
    public int getDeviceValue(@PathVariable("deviceId") Long deviceId) {
        Device device = deviceRepository.findById(deviceId).get();
        return device.getValue();
    }

    // GET the status of a Device.
    @GetMapping("/devices/getStatus/{deviceId}")
    public boolean getDevice(@PathVariable Long deviceId) {
        Device device = deviceRepository.findById(deviceId).get();
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
