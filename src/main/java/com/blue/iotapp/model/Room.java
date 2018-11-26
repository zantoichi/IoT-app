package com.blue.iotapp.model;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Device> devices;
    //Default Empty Constructor
    public Room(){

    }
    //Constructor
    public Room(String name) {
        this.name = name;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
