package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
    @Pattern(regexp = "^(^\\d{4}-\\d{4}-\\d{4}-\\d{4}$)$")
    @Column(unique = true)
    private String accountNumber;

    @Positive(message = "balance must be a Positive number")
    @Column(columnDefinition = "Double not null")
    private Double balance;

    private Boolean isActive=false;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Customer customer;
}
