package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.Room;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RoomController {
    private RoomRepository roomRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository,DeviceRepository deviceRepository) {
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    //GET a list of all Rooms.
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // Get a list of devices in a Room.
    @GetMapping("/roomdevices/{roomId}")
    public Set<Device> devicesInRoom(@PathVariable Long roomId){
        Room room = roomRepository.findById(roomId).get();
        return room.getDevices();
    }

    // ADD a device in a room by roomID and deviceID.
    @GetMapping("/rooms/{roomId}/{deviceId}")
    public Set<Device> addDeviceInRoom(@PathVariable ("roomId")Long roomId,@PathVariable("deviceId") Long deviceId){
        Room room = roomRepository.findById(roomId).get();
        Device device = deviceRepository.findById(deviceId).get();
        device.setRoom(room);
        deviceRepository.save(device);
        return  room.getDevices();
    }

    // CREATE a new room.
    @PostMapping("/rooms/newroom")
    public Room createNewRoom(@Valid @RequestBody Room room){
        roomRepository.save(room);
        return roomRepository.findByName(room.getName());
    }

    // DELETE a room by ID.
    @DeleteMapping("rooms/{roomId}")
    public void removeRoom(@PathVariable Long roomId){
        roomRepository.deleteById(roomId);
    }
}



