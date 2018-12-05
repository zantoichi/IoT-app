package com.blue.iotapp.controller;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.Room;
import com.blue.iotapp.model.User;
import com.blue.iotapp.repository.DeviceRepository;
import com.blue.iotapp.repository.RoomRepository;
import com.blue.iotapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RoomController {
    private RoomRepository roomRepository;
    private DeviceRepository deviceRepository;
    private UserRepository userRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository,DeviceRepository deviceRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository;
    }

    //GET a list of all Rooms.
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // Get a list of devices in a Room.
    @GetMapping("/roomDevices/{roomId}")
    public Set<Device> devicesInRoom(@PathVariable Long roomId){
        Room room = roomRepository.findById(roomId).get();
        return room.getDevices();
    }

    // ADD a device in a room by roomID and deviceID.
    @GetMapping("/rooms/addDevice/{roomId}/{deviceId}")
    public Set<Device> addDeviceInRoom(@PathVariable ("roomId")Long roomId,@PathVariable("deviceId") Long deviceId){
        Room room = roomRepository.findById(roomId).get();
        Device device = deviceRepository.findById(deviceId).get();
        device.setRoom(room);
        deviceRepository.save(device);
        return  room.getDevices();
    }
    // DELETE a device in a room by roomID and deviceID.
    @GetMapping("/rooms/removeDevice/{roomId}/{deviceId}")
    public Set<Device> removeDeviceFromRoom(@PathVariable ("roomId")Long roomId,@PathVariable("deviceId") Long deviceId) {
        Room room = roomRepository.findById(roomId).get();
        Device device = deviceRepository.findById(deviceId).get();

        List<User> users = userRepository.findAll();
        users.stream().map(User::getId).collect(Collectors.toList());

        for (User user: users){
            user.getDevices().remove(device);
            device.getUsers().remove(user);
        }

        room.getDevices().remove(device);
        roomRepository.save(room);

        return  room.getDevices();
    }

    // CREATE a new room.
    @PostMapping("/rooms/newRoom")
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



