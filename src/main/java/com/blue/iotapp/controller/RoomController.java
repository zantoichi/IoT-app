package com.blue.iotapp.controller;

import com.blue.iotapp.model.Room;
import com.blue.iotapp.repository.RoomRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
public class RoomController {
    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
}
