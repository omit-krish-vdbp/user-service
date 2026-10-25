
package com.krishsolution.vdbp.user_service.dto;

import com.krishsolution.vdbp.user_service.enums.KycStatus;

import java.time.LocalDateTime;

public record KycResponse(


        Long customerId,

        String documentType,

        String documentReference,

        KycStatus kycStatus,

        String rejectionReason,

        LocalDateTime verifiedAt,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {

}
