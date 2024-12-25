package com.example.security.Controller;

import com.example.security.Api.ApiResponse;
import com.example.security.Model.MyUser;
import com.example.security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/get-users")
    public ResponseEntity getAllUsers(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser myUser) {
        authService.register(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered"));
    }

}
