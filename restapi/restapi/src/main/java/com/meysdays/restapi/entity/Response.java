package com.meysdays.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    public String clientIp;
    public String greetings;
    public String location;
}
