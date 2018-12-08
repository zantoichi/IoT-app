package com.blue.iotapp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Size(max = 140)
    private String firstName;

    @Size(max = 140, message = "Last firstName is required")
    private String lastName;

    @Size(max = 140)
    private String email;

    @Size(max = 140)
    private String password;

    private String role;
}
