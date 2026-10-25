package com.krishsolution.vdbp.user_service.repository;

import com.krishsolution.vdbp.user_service.entity.KycDetails;
import com.krishsolution.vdbp.user_service.enums.KycStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KycDetailsRepository
        extends JpaRepository<KycDetails, Long> {

    Optional<KycDetails> findByCustomerId(Long customerId);

    List<KycDetails> findByKycStatus(KycStatus kycStatus);
}