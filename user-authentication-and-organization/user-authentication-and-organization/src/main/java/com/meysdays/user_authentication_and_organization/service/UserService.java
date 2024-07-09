package com.meysdays.user_authentication_and_organization.service;

import com.meysdays.user_authentication_and_organization.DTO.SignInDto;
import com.meysdays.user_authentication_and_organization.DTO.UserDto;
import com.meysdays.user_authentication_and_organization.exception.EmailAlreadyExistsException;
import com.meysdays.user_authentication_and_organization.model.*;
import com.meysdays.user_authentication_and_organization.reponse.User;
import com.meysdays.user_authentication_and_organization.repository.OrganisationRepository;
import com.meysdays.user_authentication_and_organization.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private MyUserDetailService userDetailService;

    @Transactional
    public User signUp(UserDto userDto) {

//        Optional<User> email = userRepository.findByEmail(userDto.getEmail());
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

//        Organisation organisation = new Organisation();
//        organisation.setName(organisation.getName());
//        organisation.setDescription("Your description");

        MyUser user = new MyUser();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());

//        var user = MyUser.builder()
//                .firstName(userDto.getFirstName())
//                .lastName(userDto.getLastName())
//                .email(userDto.getEmail())
//                .password(passwordEncoder.encode(userDto.getPassword()))
//                .phone(userDto.getPhone())
////                .organisations((List<Organisation>) organisation)
//                .build();

//        var org = Organisation.builder()
//                .name(user.getFirstName() + "'s " +"organisation")
//                .description("Your organisation")
//                .build();

        Organisation organisation = new Organisation();
        organisation.setName(userDto.getFirstName() + "'s " +"organisation");
        organisation.setDescription("Your description");

        user.getOrganisations().add(organisation);

//        user.addOrganisation(organisation);

//        String jwtToken = jwtService.generateToken(myUserDetailService.loadUserByUsername(user.getEmail()));

//
//        Organisation organisation = new Organisation();
//        organisation.setName(user.getFirstName() + "'s " +"organisation");
//        organisation.getUser().add(user);



//        user.getOrganisations().add(organisation);
//        organisation.getUser().add(user);
//        user.setOrganisations((List<Organisation>) org);
//        org.getUser().add(user);
//        var orgx =  organisationRepository.save(org);
//        new MyUser().addOrganisation(org);
//        new Organisation().addMyUser(user);

        userRepository.save(user);
        organisationRepository.save(organisation);


//        organisationRepository.save(org);

//        new JwtTokenHolder().setJwtToken(jwtToken);

        return new User(user.getUserId().toString(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPhone());

    }

    public UserDetails signIn(SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getEmail(),
                        signInDto.getPassword()
                )
        );

        UserDetails userDetails = userDetailService.loadUserByUsername(signInDto.getEmail());

        return userDetails;
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        signInDto.getEmail(),
//                        signInDto.getPassword()
//                )
//        );
//        if (authentication.isAuthenticated()){
//            Optional<MyUser> user = userRepository.findByEmail(signInDto.getEmail());
////            return new MyUser(user.get().getUserId(),user.get().getFirstName(),user.get().getLastName(),user.get().getEmail(),user.get().getPassword(),user.get().getPhone());
//            return jwtService.generateToken(myUserDetailService.loadUserByUsername(signInDto.getEmail()));
//        } else {
//            throw new UsernameNotFoundException("Invalid");
//        }


//        var user = userRepository.findByEmail(signInDto.getEmail())
//                .orElseThrow();
//        String jwtToken = jwtService.generateToken(myUserDetailService.loadUserByUsername(user.getEmail()));
//        new JwtTokenHolder().setJwtToken(jwtToken);

//        return new MyUser(user.getUserId().toString(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword()user.getPhone());
    }
}
