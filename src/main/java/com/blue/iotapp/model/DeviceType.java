package com.blue.iotapp.model;

import javax.persistence.*;
import java.util.Set;

/* Creating a DeviceType entity and assigning it a naming scheme
that will appear in the database. */
@Entity(name = "DeviceType")
@Table(name = "device_type")
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

    public DeviceType(String name) {
        this.name = name;
    }

    //Getters and Setters.
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
