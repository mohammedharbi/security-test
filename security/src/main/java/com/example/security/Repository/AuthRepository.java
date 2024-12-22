package com.example.security.Repository;

import com.example.security.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {


    MyUser findMyUserByUsername(String username);

}
