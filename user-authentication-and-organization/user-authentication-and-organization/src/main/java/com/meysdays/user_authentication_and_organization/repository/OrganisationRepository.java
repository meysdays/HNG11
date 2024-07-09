package com.meysdays.user_authentication_and_organization.repository;

import com.meysdays.user_authentication_and_organization.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
}
