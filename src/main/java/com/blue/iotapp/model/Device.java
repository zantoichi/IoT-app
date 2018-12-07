package com.blue.iotapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/* Creating a Device entity and assigning it a naming scheme
that will appear in the database. */
@Entity(name = "Device")
@Table(name = "device")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /* The "value" variable pseudo-represents a function for each device.
    Implemented in the front-end. */

    @PositiveOrZero
    private int value = 0;

    //Status represents an on/off state.
    private Boolean status = false;

    @NonNull
    @NotNull
    @Size(min = 3, max = 140, message = "Name must be at least 3 characters long.")
    private String name;

    /* Representing the relationship between the Device and DeviceType Entities.
    Devices can have only one device type. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
//    @Size(min=1, max = 1, message = "A a device must have 1 device type.")
    private DeviceType deviceType;

    /* Representing the relationship between the Device and Room Entities.
    A device can be in only one room. */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties("devices")
    @EqualsAndHashCode.Exclude
    private Room room;

    /* Representing the relationship between the Device and User Entities.
    A device can be assigned to many users */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            mappedBy = "devices")

    @JsonIgnoreProperties("devices")
    @EqualsAndHashCode.Exclude
    private Set<User> users = new HashSet<>();

    public Device(String name, DeviceType deviceType, Room room) {
        this.name = name;
        this.deviceType = deviceType;
        this.room = room;
    }
}
