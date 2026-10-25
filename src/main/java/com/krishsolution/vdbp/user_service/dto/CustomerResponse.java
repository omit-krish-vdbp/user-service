package com.krishsolution.vdbp.user_service.dto;

import com.krishsolution.vdbp.user_service.enums.Gender;
import com.krishsolution.vdbp.user_service.enums.MaritalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String authUserId,
        String fullName,
        LocalDate dateOfBirth,
        Gender gender,
        String occupation,
        MaritalStatus maritalStatus,
        String nationality,
        String profilePhotoUrl,
        ContactDetailsResponse contactDetails,
        KycResponse kycDetails,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}