
package com.krishsolution.vdbp.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SubmitKycRequest(

        @NotBlank(message = "Document type is required")
        @Size(max = 50, message = "Document type must not exceed 50 characters")
        String documentType,

        @NotBlank(message = "Document reference is required")
        @Size(max = 500, message = "Document reference must not exceed 500 characters")
        String documentReference

) {
}
