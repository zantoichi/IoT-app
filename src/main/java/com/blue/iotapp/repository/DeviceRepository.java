package com.blue.iotapp.repository;

import com.blue.iotapp.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Created interface that extends jparepository
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
