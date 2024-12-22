package com.example.security.Service;

import com.example.security.Model.MyUser;
import com.example.security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
}
