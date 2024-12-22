package com.example.schoolmanagement.DTO;

import com.example.schoolmanagement.Model.Address;
import com.example.schoolmanagement.Model.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TeacherDTO {

    private String name;

    private String email;

    private Set<Course> courses;
}
