package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.Api.ApiResponse;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/old-get")
    public ResponseEntity getStudent() {
        return ResponseEntity.status(200).body(studentService.findAllStudent());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
    }

    @GetMapping("/get")
    public ResponseEntity getStudentById() {
        return ResponseEntity.status(200).body(studentService.findAllStudentDTO());
    }

    @PutMapping("/change-student-major/student/{studentId}/major/{major}")
    public ResponseEntity changeStudentMajor(@PathVariable Integer studentId, @PathVariable String major) {
        studentService.changeStudentMajor(studentId, major);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }

}
