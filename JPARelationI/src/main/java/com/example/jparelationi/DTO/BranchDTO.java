package com.example.jparelationi.DTO;

import com.example.jparelationi.Model.Branch;
import com.example.jparelationi.Model.Merchant;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data

public class BranchDTO {

    @NotEmpty(message = "area is empty")
    @Size(min = 1, max = 30, message = "area min is 1 and max is 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String area;


    private Set<Merchant> merchants;
}
