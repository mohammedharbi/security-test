package com.example.schoolmanagement.DTO;

import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Model.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CourseDTO {


    private String name;

    private String teacher_name;

    private Integer registered_students;

}
