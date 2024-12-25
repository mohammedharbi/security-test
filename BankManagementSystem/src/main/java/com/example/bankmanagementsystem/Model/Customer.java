package com.example.bankmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Pattern(regexp = "^(\\+966|0)?5\\d{8}$",   message = "Phone number must start with +966 or 05 and be followed by 8 digits")
//    @Column(columnDefinition = "varchar(13) not null")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Account> accounts;
}
