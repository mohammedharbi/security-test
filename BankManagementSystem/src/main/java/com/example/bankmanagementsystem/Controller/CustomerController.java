package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.CustomerUserDTOIN;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllCustomers() {
        return ResponseEntity.status(200).body(new ApiResponse("hello"));
    }
    @PostMapping("/add-customer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerUserDTOIN customerUserDTOIN){
        authService.registerCustomer(customerUserDTOIN);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("customer added"));
    }

    @PutMapping("/update-customer")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal MyUser user, @RequestBody @Valid CustomerUserDTOIN customerUserDTOIN){
        authService.updateCustomer(user.getId(), customerUserDTOIN);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("customer updated"));
    }

    @DeleteMapping("/delete-customer")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal MyUser user){
        authService.deleteCustomer(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("customer deleted"));
    }
}
