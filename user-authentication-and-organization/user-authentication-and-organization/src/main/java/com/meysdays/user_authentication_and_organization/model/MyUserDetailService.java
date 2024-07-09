package com.meysdays.user_authentication_and_organization.model;

import com.meysdays.user_authentication_and_organization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser myUser = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with email "+email));
        return myUser;

    }

//    private String[] getRoles(User user) {
//        if (user.getRole() == null) {
//            return new String[]{"USER"};
//        }
//        return user.getRole().split(",");
//    }
}
