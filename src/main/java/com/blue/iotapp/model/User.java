package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* Creating a User entity and assigning it to a naming scheme
that will appear in the database. */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor
public class User {
    //Variable Declaration
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotBlank(message = "Name is required.")
    @Size(max=140)
    private String name;

    @NonNull
    @NotBlank(message = "Last name is required.")
    @Size(min = 3, max = 140, message = "Last name is required")
    private String lastName;

    @Email
    @NotBlank(message = "Email is required.")
    @Size(max=140)
    @NonNull
    private String email;

    @NonNull
    @NotBlank(message = "Password is required.")
    @Size(max=140)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    //Creating relation between Users and Devices
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST }
    )
    @JoinTable(name = "user_devices",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = { @JoinColumn(name = "device_id", referencedColumnName = "id") })
    @JsonIgnoreProperties("users")
    @EqualsAndHashCode.Exclude
    private Set<Device> devices = new HashSet<>();

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public User(String name, String lastName, String email, String password, List<Role> roles) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
