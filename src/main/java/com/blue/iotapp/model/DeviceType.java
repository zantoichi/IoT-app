package com.blue.iotapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DeviceType {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;

    public DeviceType() {
    }

    public DeviceType(String name) {
        this.name = name;
    }

    public Long getDeviceId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
