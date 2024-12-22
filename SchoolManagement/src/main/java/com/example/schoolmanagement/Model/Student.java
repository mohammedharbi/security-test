package com.example.schoolmanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is empty")
    @Size(min = 2, max = 20, message = "name size must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "age is null")
    @Positive(message = "must be positive")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "major is empty")
    @Size(min = 2, max = 20, message = "name size must be between 2 and 20 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

}
