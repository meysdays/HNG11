package com.meysdays.user_authentication_and_organization.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotBlank(message = "enter a name")
    private String firstName;
    @NotBlank(message = "enter a name")
    private String lastName;
    @NotBlank(message = "enter a valid email")
    private String email;
    @NotBlank(message = "cannot be blank")
    private String password;
    @NotBlank(message = "cannot be blank")
    private String phone;
}
