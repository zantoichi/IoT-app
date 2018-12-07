package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/* Creating a Room entity and assigning it to a naming scheme
that will appear in the database. */
@Entity(name = "Room")
@Table(name = "room")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Room {
    //Variable Declaration
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @Size(min=3, max=140, message = "Name must be at least 3 characters long.")
    private String name;
    /* Representing the relationship between the Room and Device Entities.
    A Room can be assigned to many devices */

    @JsonIgnoreProperties("room")
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Device> devices = new HashSet<>();
}
