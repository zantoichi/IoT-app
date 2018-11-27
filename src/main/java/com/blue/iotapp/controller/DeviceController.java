package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.repository.DeviceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DeviceController {
    private DeviceRepository deviceRepository;

    @GetMapping("/devices")
    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }
}
