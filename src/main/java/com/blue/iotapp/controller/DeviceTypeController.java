//package com.blue.iotapp.controller;
//
//import com.blue.iotapp.model.DeviceType;
//import com.blue.iotapp.repository.DeviceTypeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/api")
//public class DeviceTypeController {
//    private DeviceTypeRepository deviceTypeRepository;
//
//    @Autowired
//    public DeviceTypeController(DeviceTypeRepository deviceTypeRepository) {
//        this.deviceTypeRepository = deviceTypeRepository;
//    }
//
//    // GET a list of all device types.
//    @GetMapping("/devicetypes")
//    public List<DeviceType> getDeviceTypes(){
//        return deviceTypeRepository.findAll();
//    }
//}
