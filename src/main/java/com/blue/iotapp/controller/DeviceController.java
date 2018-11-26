package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.repository.DeviceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DeviceController {
    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/devices")
    public Collection<Device> getDevices(){
        return StreamSupport.stream(deviceRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }
}
