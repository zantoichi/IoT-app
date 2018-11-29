package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/* Creating a User entity and assigning it to a naming scheme
that will appear in the database. */
@Entity(name = "User")
@Table(name = "user")
public class User {
    //Variable Declaration
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String password;
    private String role = "USER";
    //Creating relation between Users and Devices
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_devices",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "device_id") })
    @JsonIgnoreProperties("users")
    private Set<Device> devices = new HashSet<>();
    //Default Empty Constructor
    public User() {
    }
    //Constructor
    public User(String name, String surName, String email, String password, String role) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", devices=" + devices +
                '}';
    }
}
