package com.blue.iotapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DeviceType {
    @Id
    @GeneratedValue
    private Long deviceId;
    private String name;
    @OneToMany
    Set<DeviceFunctionality> deviceFunctionalities = new HashSet<>();

    public DeviceType() {
    }

    public DeviceType(String name, Set<DeviceFunctionality> deviceFunctionalities) {
        this.name = name;
        this.deviceFunctionalities = deviceFunctionalities;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DeviceFunctionality> getInformations() {
        return deviceFunctionalities;
    }

    public void setInformations(Set<DeviceFunctionality> deviceFunctionalities) {
        this.deviceFunctionalities = deviceFunctionalities;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "deviceId=" + deviceId +
                ", name='" + name + '\'' +
                ", deviceFunctionalities=" + deviceFunctionalities +
                '}';
    }
}
