package com.krishsolution.vdbp.user_service.repository;

import com.krishsolution.vdbp.user_service.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactDetailsRepository
        extends JpaRepository<ContactDetails, Long> {

    Optional<ContactDetails> findByCustomerId(Long customerId);
}