package com.blue.iotapp.controller;

import com.blue.iotapp.model.DeviceType;
import com.blue.iotapp.repository.DeviceTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DeviceTypeController {
    private DeviceTypeRepository deviceTypeRepository;

    public DeviceTypeController(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @GetMapping("/deviceTypes")
    public Collection<DeviceType> getDeviceTypes(){
        return StreamSupport.stream(deviceTypeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }
}
