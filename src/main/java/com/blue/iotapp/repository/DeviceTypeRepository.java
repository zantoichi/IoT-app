package com.blue.iotapp.repository;

import com.blue.iotapp.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

//Created interface that extends JpaRepository
@Repository
@CrossOrigin
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    DeviceType findByName(String name);
}
