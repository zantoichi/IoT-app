package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private DeviceType deviceType;
    /* Representing the relationship between the Device and Room Entities.
    A device can be in only one room. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties("devices")
    private Room room;
    /* Representing the relationship between the Device and User Entities.
    A device can be assigned to many users */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "devices")
    @JsonIgnoreProperties("devices")
    private Set<User> users = new HashSet<>();

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", value=" + value +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", deviceType=" + deviceType +
                ", room=" + room +
                ", users=" + users +
                '}';
    }
}
