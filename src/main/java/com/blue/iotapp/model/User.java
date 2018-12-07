package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Name is required.")
    @Size(max = 140)
    private String name;

    @NonNull
    @NotBlank(message = "Last firstName is required.")
    @Size(min = 3, max = 140, message = "Last firstName is required")
    private String lastName;

    @Email
    @NotBlank(message = "Email is required.")
    @Size(max = 140)
    @NonNull
    private String email;

    @NonNull
    @NotBlank(message = "Password is required.")
    @Size(max = 140)
    @JsonIgnore
    private String password;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    //Creating relation between Users and Devices
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
    )
    @JoinTable(name = "user_devices",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id", referencedColumnName = "id")})
    @JsonIgnoreProperties("users")
    @EqualsAndHashCode.Exclude
    private Set<Device> devices = new HashSet<>();
}
