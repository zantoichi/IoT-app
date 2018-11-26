package com.blue.iotapp.repository;

import com.blue.iotapp.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

//Created interface that extends jparepository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
