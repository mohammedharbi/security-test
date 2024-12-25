package com.example.bankmanagementsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUserDTOIN {

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
    @Pattern(regexp = "^(CUSTOMER)$")
    @Column(columnDefinition = "varchar(8)")
    private String role;

    @NotEmpty(message = "position is empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String position;

    @Positive(message = "salary must be a Positive number")
    @Column(columnDefinition = "Double not null")
    private Double salary;
}
