package com.krishsolution.vdbp.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContactDetailsRequest(

        @Email(message = "Enter a valid email address")
        @Size(max = 254)
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "Enter a valid phone number"
        )
        String phoneNumber,

        @Size(max = 200)
        String addressLine,

        @Size(max = 100)
        String village,

        @Size(max = 100)
        String district,

        @Size(max = 100)
        String state,

        @Pattern(
                regexp = "^[A-Za-z0-9 -]{3,10}$",
                message = "Enter a valid postal code"
        )
        String postalCode,

        @Size(max = 100)
        String country
) {
}