
package com.krishsolution.vdbp.user_service.dto;

import com.krishsolution.vdbp.user_service.enums.Gender;
import com.krishsolution.vdbp.user_service.enums.MaritalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateCustomerRequest(

        @NotBlank(message = "Auth user ID is required")
        @Size(max = 100)
        String authUserId,

        @NotBlank(message = "Full name is required")
        @Size(max = 100, message = "Full name must not exceed 100 characters")
        String fullName,

        @Past(message = "Date of birth must be in the past")
        LocalDate dateOfBirth,

        Gender gender,

        @Size(max = 100)
        String occupation,

        MaritalStatus maritalStatus,

        @Size(max = 60)
        String nationality,

        @Size(max = 500)
        String profilePhotoUrl,

        @Valid
        @NotNull(message = "Contact details are required")
        ContactDetailsRequest contactDetails,

        @Valid
        @NotNull(message = "KYC details are required")
        SubmitKycRequest submitKycRequest

) {
}
