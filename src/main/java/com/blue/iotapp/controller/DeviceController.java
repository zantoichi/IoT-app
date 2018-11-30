package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DeviceController {
    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/devices")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    //TODO: Fix this, dear lord...
    //@DeleteMapping("/devices/{deviceId}")
   // public void removeDevice(@PathVariable Long deviceId) {
     //   deviceRepository.deleteById(deviceId);
   // }
    @PostMapping("/newdevice")
    public List<Device> putNewDevice(@Valid @RequestBody Device device) {
        deviceRepository.save(device);
        log.info("device:" + device);
        return deviceRepository.findAll();
    }

    @GetMapping("devices/{id}/{changevalue}")
    public Device changeDeviceValue(@PathVariable("id") Long id, @PathVariable("changevalue") int changevalue) {
        Device device = deviceRepository.findById(id).get();
        device.setValue(changevalue);
        deviceRepository.save(device);
        return device;
    }

    @GetMapping("devices/{id}/getvalue")
    public int getDeviceValue(@PathVariable("id") Long id) {
        Device device = deviceRepository.findById(id).get();
        return device.getValue();
    }

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
    //TODO: Fetch a list of Devices and their Users
    @GetMapping("devices/users/{deviceId}")
    public List<User> getDeviceAndUsers(@PathVariable Long deviceId){
        return deviceRepository.getDevicesAndTheirUsersByDeviceId(deviceId);
    }
}
