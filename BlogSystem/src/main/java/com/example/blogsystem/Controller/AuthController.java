package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiResponse;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.BlogRepository;
import com.example.blogsystem.Service.AuthService;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final BlogService blogService;

    @GetMapping("/get-all-user-blogs")
    public ResponseEntity getAllUserBlogs(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.status(200).body(blogService.getAllUserBlogs());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser user) {
        authService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("user registered"));
    }

}
