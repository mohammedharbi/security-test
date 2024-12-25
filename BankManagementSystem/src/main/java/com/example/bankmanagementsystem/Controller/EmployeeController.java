package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.DTO.CustomerUserDTOIN;
import com.example.bankmanagementsystem.DTO.EmployeeUserDTOIN;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import com.example.bankmanagementsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final AuthService authService;

    @PostMapping("/add-employee")
    public ResponseEntity addEmployee(@RequestBody @Valid EmployeeUserDTOIN employeeUserDTOIN){
        authService.registerEmployee(employeeUserDTOIN);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added");
    }
    @PutMapping("/update-employee")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser user, @RequestBody @Valid EmployeeUserDTOIN employeeUserDTOIN){
        authService.updateEmployee(user.getId(), employeeUserDTOIN);
        return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
    }

    @DeleteMapping("/delete-employee")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser user){
        authService.deleteEmployee(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }
}
