package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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

    //Find device by ID.
    @GetMapping("/devices/{deviceId}")
    public Device findDevice (@PathVariable Long deviceId) {
        return deviceRepository.findById(deviceId).get();
    }
    // Delete device by ID.
    @DeleteMapping("/devices/{deviceId}")
    public void removeDevice(@PathVariable Long deviceId) {
    deviceRepository.deleteById(deviceId);
   }

    //Create new device through JSON.
    @PostMapping("/newdevice")
    public List<Device> newDevice(@Valid @RequestBody Device device) {
        deviceRepository.save(device);
        log.info("device:" + device);
        return deviceRepository.findAll();
    }

    // Change the VALUE property of a device.
    @GetMapping("devices/{deviceId}/{changevalue}")
    public Device changeDeviceValue(@PathVariable("id") Long deviceId, @PathVariable("changevalue") int changevalue) {
        Device device = deviceRepository.findById(deviceId).get();
        device.setValue(changevalue);
        deviceRepository.save(device);
        return device;
    }
    // GET the value property of a device.
    @GetMapping("devices/{deviceId}/getvalue")
    public int getDeviceValue(@PathVariable("deviceId") Long deviceId) {
        Device device = deviceRepository.findById(deviceId).get();
        return device.getValue();
    }

    // GET the status of a Device.
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
}
