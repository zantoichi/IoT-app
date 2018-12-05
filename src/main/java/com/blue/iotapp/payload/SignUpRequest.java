package com.blue.iotapp.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @Email
    @Size(max = 40)
    @NotBlank
    private String email;
}
