package com.meysdays.user_authentication_and_organization.reponse;

import lombok.*;

@lombok.Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Data {
    private String accessToken;
    private User userResponse;


}
