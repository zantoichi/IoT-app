package com.blue.iotapp.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @NonNull
    private String name;
}
