package com.blue.iotapp.payload;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Size(max = 140)
    private String name;


    @Size(min = 3, max = 140, message = "Last name is required")
    private String lastName;

    @Size(max = 140)
    private String email;

    @Size(max = 140)
    private String password;

    private String role;
}
