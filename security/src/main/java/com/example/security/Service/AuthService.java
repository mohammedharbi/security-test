package com.example.security.Service;

import com.example.security.Api.ApiException;
import com.example.security.Model.MyUser;
import com.example.security.Repository.AuthRepository;
import com.example.security.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void register(MyUser user) {
        user.setRole("USER");
        String HashPass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(HashPass);
        authRepository.save(user);
    }

    public List<MyUser> getAllUsers() {
        return authRepository.findAll();
    }
}
