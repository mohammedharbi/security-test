package com.example.jparelationi.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDTO {

    @NotEmpty(message = "name is empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;
}
