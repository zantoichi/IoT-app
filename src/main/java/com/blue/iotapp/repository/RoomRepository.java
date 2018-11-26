package com.blue.iotapp.repository;

import com.blue.iotapp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Created interface that extends Jpa Repository
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query
    Room findByName(String name);
}
