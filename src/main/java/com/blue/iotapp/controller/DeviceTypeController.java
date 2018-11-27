package com.blue.iotapp.controller;

import com.blue.iotapp.model.DeviceType;
import com.blue.iotapp.repository.DeviceTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DeviceTypeController {
    private DeviceTypeRepository deviceTypeRepository;

    public DeviceTypeController(DeviceTypeRepository deviceTypeepository) {
        this.deviceTypeRepository = deviceTypeepository;
    }

    @GetMapping("/devicetypes")
    public List<DeviceType> getDeviceTypes(){
        return deviceTypeRepository.findAll();
    }
}
