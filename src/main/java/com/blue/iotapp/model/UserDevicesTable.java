package com.blue.iotapp.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "USER_DEVICES")
@Data
public class UserDevicesTable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Device device;

}
