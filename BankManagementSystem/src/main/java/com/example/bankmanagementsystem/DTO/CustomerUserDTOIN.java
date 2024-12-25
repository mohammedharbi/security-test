package com.example.bankmanagementsystem.DTO;

import com.example.bankmanagementsystem.Model.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUserDTOIN {

    @NotEmpty(message = "username is empty")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotEmpty(message = "password is empty")
    @Size(min = 6, max = 20, message = "password must be between 6 and 20")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "name is empty")
    @Size(min = 2, max = 20, message = "varchar(20) not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "Please enter a valid email format")
    @Column(unique = true)
    private String email;

    //@NotEmpty(message = "role is empty")
    private String role;

    @Pattern(regexp = "^(\\+966|0)?5\\d{8}$",   message = "Phone number must start with +966 or 05 and be followed by 8 digits")
    @Column(columnDefinition = "varchar(13) not null")
    private String phoneNumber;

}
