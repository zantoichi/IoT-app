package com.blue.iotapp.model;

import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

/* Creating a DeviceType entity and assigning it a naming scheme
that will appear in the database. */
@Entity(name = "DeviceType")
@Table(name = "device_type")
@ToString(exclude = "Device")
public class DeviceType {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    /* Representing the relationship between the DeviceType and Device Entities.
    A device type can be assigned to many devices */
    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Device> devices;

    //Default empty constructor.
    public DeviceType() {
    }

    public DeviceType (String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Device> getDeviceType() {
        return devices;
    }

    public void setDeviceType(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }
}