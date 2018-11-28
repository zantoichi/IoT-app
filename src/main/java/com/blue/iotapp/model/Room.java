package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/* Creating a Room entity and assigning it to a naming scheme
that will appear in the database. */
@Entity(name = "Room")
@Table(name = "room")
public class Room {
    //Variable Declaration
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /* Representing the relationship between the Room and Device Entities.
    A Room can be assigned to many devices */
    @JsonIgnoreProperties("room")
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Device> devices = new HashSet<>();

    public Room(String name) {
        this.name = name;
    }

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devices=" + devices +
                '}';
    }
}
