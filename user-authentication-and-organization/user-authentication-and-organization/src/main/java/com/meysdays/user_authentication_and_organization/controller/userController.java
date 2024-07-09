package com.meysdays.user_authentication_and_organization.controller;

import com.meysdays.user_authentication_and_organization.DTO.SignInDto;
import com.meysdays.user_authentication_and_organization.DTO.UserDto;
import com.meysdays.user_authentication_and_organization.exception.EmailAlreadyExistsException;
import com.meysdays.user_authentication_and_organization.model.MyUser;
import com.meysdays.user_authentication_and_organization.model.MyUserDetailService;
import com.meysdays.user_authentication_and_organization.reponse.Data;
import com.meysdays.user_authentication_and_organization.reponse.Response;
import com.meysdays.user_authentication_and_organization.reponse.UnsuccessfulResponse;
import com.meysdays.user_authentication_and_organization.reponse.User;
import com.meysdays.user_authentication_and_organization.repository.UserRepository;
import com.meysdays.user_authentication_and_organization.service.JwtService;
import com.meysdays.user_authentication_and_organization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class userController {

    @Autowired
    public UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/register")
    public ResponseEntity<?>  signUp(@Validated @RequestBody UserDto userDto){

        try {
            User userResponse = userService.signUp(userDto);
            String jwtToken = jwtService.generateToken(myUserDetailService.loadUserByUsername(userDto.getEmail()));
            Response response = new Response("success", "Registration successful", new Data(jwtToken, userResponse));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            UnsuccessfulResponse errorResponse = new UnsuccessfulResponse("Bad Request", "Registration unsuccessful", 401);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            Response errorResponse = new Response("error", e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Validated @RequestBody SignInDto signInDto){
        try {
            MyUser user = (MyUser) userService.signIn(signInDto);
            String jwt = jwtService.generateToken(myUserDetailService.loadUserByUsername(signInDto.getEmail()));
            Response response = new Response("success", "Login successful",new Data(jwt, new User(
                    user.getUserId().toString(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone()
            )));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            UnsuccessfulResponse errorResponse = new UnsuccessfulResponse("Bad", "Authentication failed", 401);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            UnsuccessfulResponse errorResponse = new UnsuccessfulResponse("Bad request", "Authentication failed", 401);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        try {
//            String userResponse = userService.signIn(signInDto);
//            UnsuccessfulResponse response = new UnsuccessfulResponse("success",userResponse, 200);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }catch (UsernameNotFoundException e){
//            Response errorResponse = new Response("Bad request", "Authentication", 401);
//            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            Response errorResponse = new Response("error", e.getMessage(), null);
//            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }


}
