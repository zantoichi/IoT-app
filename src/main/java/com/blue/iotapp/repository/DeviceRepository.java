package com.blue.iotapp.repository;

import com.blue.iotapp.model.Device;
import com.blue.iotapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//Created interface that extends jparepository
@Repository
@CrossOrigin
public interface DeviceRepository extends JpaRepository<Device, Long> {
    //TODO: Fetch a list of Devices and their Users
    @Query("select device.users from Device device where device.id = :deviceId")
    List<User> getDevicesAndTheirUsersByDeviceId(@Param("deviceId") Long deviceId);
}
