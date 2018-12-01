package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
/* Creating a User entity and assigning it to a naming scheme
that will appear in the database. */
@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {
    //Variable Declaration
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(max=140)
    private String name;
    @NotBlank(message = "Last name is required.")
    @Size(min = 3, max = 140, message = "Last name is required")
    private String lastName;
    @Email
    @NotBlank(message = "Email is required.")
    @Size(max=140)
    private String email;
    @NotBlank(message = "Password is required.")
    @Size(max=140)
    private String password;

    private String role;
    //Creating relation between Users and Devices
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinTable(name = "user_devices",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "device_id", referencedColumnName = "id") })
    @JsonIgnoreProperties("users")
    private Set<Device> devices = new HashSet<>();

    //Constructor
    public User(String name, String lastName, String email, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
