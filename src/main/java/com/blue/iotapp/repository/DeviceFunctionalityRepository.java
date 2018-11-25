package com.blue.iotapp.repository;

import com.blue.iotapp.model.DeviceFunctionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceFunctionalityRepository extends JpaRepository<DeviceFunctionality, Long> {
}
