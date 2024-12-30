package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }
}
