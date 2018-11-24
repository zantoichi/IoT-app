package com.blue.iotapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;
    private  Boolean status = false;
    private String name;
    @ManyToOne
    private DeviceType deviceType;
    @ManyToOne
    private  Room room;
    @ManyToMany
    private Set<User> users = new HashSet<>();

    public Device() {
    }

    public Device(String name, DeviceType deviceType, Room room) {
        this.name = name;
        this.deviceType = deviceType;
        this.room = room;
    }

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
                ", status=" + status +
                ", name='" + name + '\'' +
                ", deviceType=" + deviceType +
                ", room=" + room +
                '}';
    }
}
