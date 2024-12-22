package com.example.jparelationi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "merchant")
    private Set<Branch> branches;

    @ManyToMany(mappedBy = "merchants")
    private Set<Customer> customers;
}
