package com.blue.iotapp.repository;

import com.blue.iotapp.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
