package com.blue.iotapp.model;

import javax.persistence.*;
import java.util.Set;

/* Creating a Device entity and assigning it a naming scheme
that will appear in the database. */
@Entity(name = "Device")
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    /* The "value" variable pseudo-represents a function for each device.
    Implemented in the front-end. */
    private int value;
    //Status represents an on/off state.
    private Boolean status = false;
    private String name;
    /* Representing the relationship between the Device and DeviceType Entities.
    Devices can have only one device type. */
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceType deviceType;
    /* Representing the relationship between the Device and Room Entities.
    A device can be in only one room. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private  Room room;
    /* Representing the relationship between the Device and User Entities.
    A device can be assigned to many users */
    @ManyToMany
    private Set<User> users;

    //Default empty constructor.
    public Device() {
    }

    public Device(String name, DeviceType deviceType, Room room) {
        this.name = name;
        this.deviceType = deviceType;
        this.room = room;
    }

    //Getters and Setters.
    public Long getId() {
        return id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return deviceType;
    }

    public void setType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", value=" + value +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
