package com.meysdays.user_authentication_and_organization.reponse;

import lombok.*;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnsuccessfulResponse{
    private String status;
    private String message;
    private int statusCode;
}
