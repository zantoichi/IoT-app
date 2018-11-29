package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.Room;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin
//@RequestMapping("/api")
public class RoomController {
    private RoomRepository roomRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository,DeviceRepository deviceRepository) {
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/roomdevices/{id}")
    public Set<Device> devicesInRoom(@PathVariable Long id){
        Room room = roomRepository.findById(id).get();
        return room.getDevices();
    }

    @GetMapping("/rooms/{roomId}/{deviceId}")
    public Set<Device> addDeviceInRoom(@PathVariable ("roomId")Long roomId,@PathVariable("deviceId") Long deviceId){
        Room room = roomRepository.findById(roomId).get();
        Device device = deviceRepository.findById(deviceId).get();
        device.setRoom(room);
        deviceRepository.save(device);
        return  room.getDevices();
    }
    @PostMapping("/rooms/newroom")
    public Room createNewRoom(@Valid @RequestBody Room room){
        roomRepository.save(room);
        log.info("Room:" + room);
        return roomRepository.findByName(room.getName());
    }
}



