package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.repository.DeviceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {
    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/devices")
    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }
    //TODO: Fix this, dear lord...
//    @DeleteMapping("/devices/{deviceId}")
//    public List<Device> removeDevice(@PathVariable Long deviceId) {
//        deviceRepository.deleteById(deviceId);
//        List<Device> devices = deviceRepository.findAll();
//        return devices;
//    }
    @PutMapping("/newdevice")
    public List<Device> putNewDevice(@RequestBody Device device){
        deviceRepository.save(device);
        return deviceRepository.findAll();
    }
}
