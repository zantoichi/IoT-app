package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DeviceController {
    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/devices")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    //TODO: Fix this, dear lord...
//    @DeleteMapping("/devices/{deviceId}")
//    public List<Device> removeDevice(@PathVariable Long deviceId) {
//        deviceRepository.deleteById(deviceId);
//        List<Device> devices = deviceRepository.findAll();
//        return devices;
//    }
    @PostMapping("/newdevice")
    public List<Device> putNewDevice(@RequestBody Device device) {
        deviceRepository.save(device);
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
//    @GetMapping("devices/users")
//    public List<Object> getDevicesAndTheirUsers(){
//        return deviceRepository.getDeviceAndUsers();
//    }
}
