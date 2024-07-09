package com.meysdays.user_authentication_and_organization.reponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
