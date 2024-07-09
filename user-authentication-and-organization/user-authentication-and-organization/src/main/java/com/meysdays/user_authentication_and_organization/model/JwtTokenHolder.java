package com.meysdays.user_authentication_and_organization.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class JwtTokenHolder {
    private String jwtToken;
}
