package com.blue.iotapp.controller;

import com.blue.iotapp.model.Room;
import com.blue.iotapp.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RoomController {
    private RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public Collection<Room> getRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
