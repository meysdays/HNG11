package com.meysdays.user_authentication_and_organization.service;

import com.meysdays.user_authentication_and_organization.model.MyUser;
import com.meysdays.user_authentication_and_organization.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppService {

    @Autowired
    private UserRepository userRepository;

    public MyUser getUserById(Long id) {
        Optional<MyUser> user = userRepository.findById(id);
        return new MyUser(
                user.get().getUserId(),
                user.get().getFirstName(),
                user.get().getLastName(),
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getPhone(),
                user.get().getOrganisations()
        );
    }
}
