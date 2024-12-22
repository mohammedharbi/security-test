package com.example.schoolmanagement.DTO;

import com.example.schoolmanagement.Model.Course;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class StudentDTO {

    private String name;

    private String major;

    private Set<Course> courses;

}
