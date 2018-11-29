package com.blue.iotapp.repository;

import com.blue.iotapp.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

//Created interface that extends jparepository
@Repository
@CrossOrigin
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
