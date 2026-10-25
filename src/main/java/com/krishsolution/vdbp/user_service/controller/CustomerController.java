package com.krishsolution.vdbp.user_service.controller;

import com.krishsolution.vdbp.user_service.dto.CreateCustomerRequest;
import com.krishsolution.vdbp.user_service.dto.CustomerResponse;
import com.krishsolution.vdbp.user_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/profile")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createProfile(
            @Valid @RequestBody CreateCustomerRequest request) {

        CustomerResponse response =
                customerService.createProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{authUserId}")
    public ResponseEntity<CustomerResponse> getProfile(
            @PathVariable String authUserId) {

        return ResponseEntity.ok(
                customerService.getProfile(authUserId)
        );
    }

    @PutMapping("/{authUserId}")
    public ResponseEntity<CustomerResponse> updateProfile(
            @PathVariable String authUserId,
            @Valid @RequestBody CreateCustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateProfile(authUserId, request)
        );
    }
}