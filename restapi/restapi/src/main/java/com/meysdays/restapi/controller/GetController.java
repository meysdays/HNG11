package com.meysdays.restapi.controller;

import com.meysdays.restapi.entity.Response;
import com.meysdays.restapi.service.GetInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetController {

    @Autowired
    GetInfoService getInfoService;

    @GetMapping("/hello")
    public ResponseEntity<Response> getClientIp(@RequestParam(value = "visitorName", defaultValue = "enter your name in the url") String userName, HttpServletRequest request){
        Response res = getInfoService.getInfoService(request, userName);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
