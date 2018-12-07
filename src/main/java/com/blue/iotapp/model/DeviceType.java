package com.blue.iotapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/* Creating a DeviceType entity and assigning it a naming scheme
that will appear in the database. */
@Entity
@Table
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NonNull
    @NotNull
    @Size(max = 140)
    private String name;
    /* Representing the relationship between the DeviceType and Device Entities.
    A device type can be assigned to many devices */
    @OneToMany(mappedBy = "deviceType", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<Device> devices;
}