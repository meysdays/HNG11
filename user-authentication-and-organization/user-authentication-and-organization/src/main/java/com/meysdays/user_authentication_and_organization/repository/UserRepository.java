package com.meysdays.user_authentication_and_organization.repository;

import com.meysdays.user_authentication_and_organization.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByEmail(String email);
}
