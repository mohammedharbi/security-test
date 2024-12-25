package com.example.bankmanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @NotEmpty(message = "username is empty")
//    @Size(min = 4, max = 10, message = "username must be between 4 and 10")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

//    @NotEmpty(message = "password is empty")
//    @Size(min = 6, max = 20, message = "password must be between 6 and 20")
    @Column(columnDefinition = "varchar(9999) not null")
    private String password;

//    @NotEmpty(message = "name is empty")
//    @Size(min = 2, max = 20, message = "varchar(20) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "Please enter a valid email format")
    @Column(unique = true)
    private String email;

    //@NotEmpty(message = "role is empty")
    private String role;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    ////////////////////////////////////////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
