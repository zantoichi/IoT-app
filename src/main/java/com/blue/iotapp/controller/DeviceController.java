package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.repository.DeviceRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @DeleteMapping("/devices/removeDevice/{deviceId}")
    public List<Device> removeDevice(@PathVariable Long deviceId) {
        deviceRepository.deleteById(deviceId);
        List<Device> devices = deviceRepository.findAll();
        return devices;
    }
}
