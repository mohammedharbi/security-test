package com.example.jparelationi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {

    @Id
    private Integer id;

    @NotEmpty
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty(message = "gender is empty")
    @Column(columnDefinition = "varchar(6) not null")
    private String gender;

    @NotNull(message = "age is empty")
    @Min(value = 18, message = "age must be higher than 18")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @Email
    @Column(unique = true)
    private String email;


    @OneToOne
    @MapsId
    @JsonIgnore
    private Customer customer;

}