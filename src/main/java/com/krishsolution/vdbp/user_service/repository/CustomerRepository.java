package com.krishsolution.vdbp.user_service.repository;

import com.krishsolution.vdbp.user_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

    Optional<Customer> findByAuthUserId(String authUserId);

    boolean existsByAuthUserId(String authUserId);
}