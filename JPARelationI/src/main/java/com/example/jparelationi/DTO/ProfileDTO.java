package com.example.jparelationi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTO {

    private Integer customer_id;

    @NotEmpty(message = "Phone number is empty")
    private String phoneNumber;

    @NotEmpty(message = "gender is empty")
    private String gender;

    @NotNull(message = "age is empty")
    @Min(value = 18, message = "age must be higher than 18")
    private Integer age;

    @Email
    private String email;

}
