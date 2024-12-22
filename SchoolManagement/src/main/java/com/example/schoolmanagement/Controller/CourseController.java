package com.example.schoolmanagement.Controller;

import com.example.schoolmanagement.Api.ApiResponse;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/old-get")
    public ResponseEntity getCourse() {
        return ResponseEntity.status(200).body(courseService.findAllCourses());
    }

    @GetMapping("/get")
    public ResponseEntity findAllCoursesDTO(){
        return ResponseEntity.status(200).body(courseService.findAllCoursesDTO());
    }

    @PostMapping("/add/{teacher_id}")
    public ResponseEntity addCourse(@PathVariable Integer teacher_id,@RequestBody @Valid Course course) {
        courseService.addCourse(teacher_id,course);
        return ResponseEntity.status(200).body(new ApiResponse("course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course) {
        courseService.updateCourse(id,course);
        return ResponseEntity.status(200).body(new ApiResponse("course updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body(new ApiResponse("course deleted"));
    }

    @GetMapping("/find-course-teacher-by-course/{course_id}")
    public ResponseEntity findCourseTeacherByCourse(@PathVariable Integer course_id) {
        return ResponseEntity.status(200).body(courseService.courseTeacher(course_id));
    }

    @PutMapping("/assign-course-to-student/course/{course_id}/student/{student_id}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer course_id, @PathVariable Integer student_id) {
        courseService.assignCourseToStudent(course_id,student_id);
        return ResponseEntity.status(200).body(new ApiResponse("course assigned"));
    }

    @GetMapping("/get-student-by-course_id/{course_id}")
    public ResponseEntity findStudentByCourseId(@PathVariable Integer course_id) {
        return ResponseEntity.status(200).body(courseService.getStudentByCourseId(course_id));
    }

}