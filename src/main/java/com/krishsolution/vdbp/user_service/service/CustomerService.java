
package com.krishsolution.vdbp.user_service.service;

import com.krishsolution.vdbp.user_service.dto.*;
import com.krishsolution.vdbp.user_service.entity.ContactDetails;
import com.krishsolution.vdbp.user_service.entity.Customer;
import com.krishsolution.vdbp.user_service.entity.KycDetails;
import com.krishsolution.vdbp.user_service.enums.KycStatus;
import com.krishsolution.vdbp.user_service.repository.CustomerRepository;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerResponse createProfile(CreateCustomerRequest request) {

        if (customerRepository.existsByAuthUserId(request.authUserId())) {
            throw new IllegalArgumentException(
                    "A profile already exists for this auth user"
            );
        }

        Customer customer = new Customer();
        customer.setAuthUserId(request.authUserId());
        customer.setFullName(request.fullName());
        customer.setDateOfBirth(request.dateOfBirth());
        customer.setGender(request.gender());
        customer.setOccupation(request.occupation());
        customer.setMaritalStatus(request.maritalStatus());
        customer.setNationality(request.nationality());
        customer.setProfilePhotoUrl(request.profilePhotoUrl());

        ContactDetails contact = getContactDetails(request);

        // Set both sides of the one-to-one relationship.
        customer.setContactDetails(contact);
        //set KYC details
        KycDetails kycDetails = getKycDetails(request);
        customer.setKycDetails(kycDetails);

        Customer savedCustomer = customerRepository.save(customer);

        return toResponse(savedCustomer);
    }

    private static KycDetails getKycDetails(CreateCustomerRequest request) {
        SubmitKycRequest kycRequest = request.submitKycRequest();
        LocalDateTime now = LocalDateTime.now();
        KycDetails kycDetails = new KycDetails();
        kycDetails.setKycStatus(KycStatus.PENDING);
        kycDetails.setVerifiedAt(now);
        kycDetails.setRejectionReason(null);
        kycDetails.setDocumentType(kycRequest.documentType());
        kycDetails.setDocumentReference(kycDetails.getDocumentReference());
        return kycDetails;
    }
    private static ContactDetails getContactDetails(CreateCustomerRequest request) {
        ContactDetailsRequest contactRequest = request.contactDetails();

        ContactDetails contact = new ContactDetails();
        contact.setEmail(contactRequest.email());
        contact.setPhoneNumber(contactRequest.phoneNumber());
        contact.setAddressLine(contactRequest.addressLine());
        contact.setVillage(contactRequest.village());
        contact.setDistrict(contactRequest.district());
        contact.setState(contactRequest.state());
        contact.setPostalCode(contactRequest.postalCode());
        contact.setCountry(contactRequest.country());
        return contact;
    }

    @Transactional(readOnly = true)
    public CustomerResponse getProfile(String authUserId) {

        Customer customer = customerRepository
                .findByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer profile not found"
                ));

        return toResponse(customer);
    }

    @Transactional
    public CustomerResponse updateProfile(
            String authUserId,
            CreateCustomerRequest request) {

        Customer customer = customerRepository
                .findByAuthUserId(authUserId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer profile not found"
                ));

        customer.setFullName(request.fullName());
        customer.setDateOfBirth(request.dateOfBirth());
        customer.setGender(request.gender());
        customer.setOccupation(request.occupation());
        customer.setMaritalStatus(request.maritalStatus());
        customer.setNationality(request.nationality());
        customer.setProfilePhotoUrl(request.profilePhotoUrl());

        ContactDetailsRequest contactRequest = request.contactDetails();

        ContactDetails contact = customer.getContactDetails();

        if (contact == null) {
            contact = new ContactDetails();
            customer.setContactDetails(contact);
        }

        contact.setEmail(contactRequest.email());
        contact.setPhoneNumber(contactRequest.phoneNumber());
        contact.setAddressLine(contactRequest.addressLine());
        contact.setVillage(contactRequest.village());
        contact.setDistrict(contactRequest.district());
        contact.setState(contactRequest.state());
        contact.setPostalCode(contactRequest.postalCode());
        contact.setCountry(contactRequest.country());

        return toResponse(customer);
    }

    private CustomerResponse toResponse(Customer customer) {

        ContactDetails contact = customer.getContactDetails();

        ContactDetailsResponse contactResponse = getContactDetailsResponse(contact);

        KycDetails kycDetails=  customer.getKycDetails();

        KycResponse kycResponse =getKycDetailsResponse(kycDetails);


        return new CustomerResponse(
                customer.getId(),
                customer.getAuthUserId(),
                customer.getFullName(),
                customer.getDateOfBirth(),
                customer.getGender(),
                customer.getOccupation(),
                customer.getMaritalStatus(),
                customer.getNationality(),
                customer.getProfilePhotoUrl(),
                contactResponse,
                kycResponse,

                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }

    private KycResponse getKycDetailsResponse(KycDetails kycDetails) {

        KycResponse kycResponse=null;


        if (kycDetails != null) {
            kycResponse = new KycResponse(
                    kycDetails.getCustomer().getId(),
                    kycDetails.getDocumentType(),
                    kycDetails.getDocumentReference(),
                    kycDetails.getKycStatus(),
                    kycDetails.getRejectionReason(),
                    kycDetails.getVerifiedAt(),
                    kycDetails.getCreatedAt(),
                    kycDetails.getUpdatedAt()
            );

        }
        return kycResponse;
    }

    private static ContactDetailsResponse getContactDetailsResponse(ContactDetails contact) {
        ContactDetailsResponse contactResponse = null;

        if (contact != null) {
            contactResponse = new ContactDetailsResponse(
                    contact.getEmail(),
                    contact.getPhoneNumber(),
                    contact.getAddressLine(),
                    contact.getVillage(),
                    contact.getDistrict(),
                    contact.getState(),
                    contact.getPostalCode(),
                    contact.getCountry()
            );
        }
        return contactResponse;
    }
}
