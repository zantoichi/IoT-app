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
@RequiredArgsConstructor
public class User {
    //Variable Declaration
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @NotBlank(message = "Name is required.")
    @Size(max = 140)
    private String name;

    @NonNull
    @NotBlank(message = "Last name is required.")
    @Size(min = 3, max = 140, message = "Last name is required")
    private String lastName;

    @Email
    @NotBlank(message = "Email is required.")
    @Size(max = 140)
    @NonNull
    private String email;

    @NonNull
    @NotBlank(message = "Password is required.")
    @Size(max = 140)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //Creating relation between Users and Devices
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserDevicesTable> userDevices = new HashSet<>();
}
