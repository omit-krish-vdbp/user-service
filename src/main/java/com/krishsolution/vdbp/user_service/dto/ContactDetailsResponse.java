package com.krishsolution.vdbp.user_service.dto;

public record ContactDetailsResponse(
        String email,
        String phoneNumber,
        String addressLine,
        String village,
        String district,
        String state,
        String postalCode,
        String country
) {
}