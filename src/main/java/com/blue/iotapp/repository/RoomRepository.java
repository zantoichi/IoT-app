package com.blue.iotapp.repository;

import com.blue.iotapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

//Created interface that extends Jpa Repository
@Repository
@CrossOrigin

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByName(String name);
}
