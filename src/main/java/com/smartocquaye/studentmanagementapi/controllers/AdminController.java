package com.smartocquaye.studentmanagementapi.controllers;

import com.smartocquaye.studentmanagementapi.dtos.AccountResponse;
import com.smartocquaye.studentmanagementapi.dtos.CreateAccountRequest;
import com.smartocquaye.studentmanagementapi.services.AccountProvisioningService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AccountProvisioningService accountProvisioningService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody CreateAccountRequest request) {
        AccountResponse response = accountProvisioningService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
