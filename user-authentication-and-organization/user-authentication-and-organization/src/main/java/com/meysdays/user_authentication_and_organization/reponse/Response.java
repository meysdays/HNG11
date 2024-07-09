package com.meysdays.user_authentication_and_organization.reponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    private String status;
    private String message;
    private T data;
}
