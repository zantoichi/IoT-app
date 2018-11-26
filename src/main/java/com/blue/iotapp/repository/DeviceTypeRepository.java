package com.blue.iotapp.repository;

import com.blue.iotapp.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Created interface that extends JpaRepository
@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {
    @Query
    DeviceType findByName(String name);
}
