package com.meysdays.user_authentication_and_organization.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
    @NotBlank(message = "cannot be blank")
    private String email;
    @NotBlank(message = "input your password")
    private String password;
}
