package com.example.blogsystem.Service;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.MyUser;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    public void register(MyUser user) {
        user.setRole("USER");
        String password =new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        authRepository.save(user);
    }


}
