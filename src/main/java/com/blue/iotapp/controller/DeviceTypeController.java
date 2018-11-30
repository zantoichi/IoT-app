package com.blue.iotapp.controller;

import com.blue.iotapp.model.DeviceType;
import com.blue.iotapp.repository.DeviceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class DeviceTypeController {
    private DeviceTypeRepository deviceTypeRepository;

    @Autowired
    public DeviceTypeController(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @GetMapping("/devicetypes")
    public List<DeviceType> getDeviceTypes(){
        return deviceTypeRepository.findAll();
    }
}
