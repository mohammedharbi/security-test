package com.example.schoolmanagement.DTO;

import com.example.schoolmanagement.Model.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressOutDTO {

    private String area;

    private String street;

    private Integer buildingNumber;

    private String teacher_name;
}
