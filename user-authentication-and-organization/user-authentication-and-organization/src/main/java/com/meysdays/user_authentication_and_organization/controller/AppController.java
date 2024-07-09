package com.meysdays.user_authentication_and_organization.controller;

import com.meysdays.user_authentication_and_organization.model.MyUser;
import com.meysdays.user_authentication_and_organization.reponse.Data;
import com.meysdays.user_authentication_and_organization.reponse.Response;
import com.meysdays.user_authentication_and_organization.reponse.User;
import com.meysdays.user_authentication_and_organization.repository.UserRepository;
import com.meysdays.user_authentication_and_organization.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/good")
    public String hello(){
        return "why na";
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id){

        MyUser user = appService.getUserById(id);

        User users = new User();
        users.setUserId(users.getUserId());
        users.setFirstName(users.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        users.setPhone(users.getPhone());

//        User users = User.builder()
//                user.getUserId(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail(),
//                user.getPhone()
//                .build();

        Response response = new Response("success", "<message>",new Data("", users));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//        return new ResponseEntity<>(response, HttpStatus.OK);
    }

