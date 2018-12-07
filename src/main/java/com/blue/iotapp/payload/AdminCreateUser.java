package com.blue.iotapp.payload;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCreateUser {
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

    private String role;
}
