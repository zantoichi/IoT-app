package com.blue.iotapp.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "DeviceType")
@Table(name = "device_type")
public class DeviceType {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Device> device;

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
