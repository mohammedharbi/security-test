package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.DTO.StudentDTO;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public List<StudentDTO> findAllStudentDTO() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTOS1 = new StudentDTO(student.getName(),student.getMajor(),student.getCourses());
            studentDTOS.add(studentDTOS1);
        }
        return studentDTOS;
    }



    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id,Student student) {
        Student stud = studentRepository.findStudentById(id);
        if (stud != null) {
            stud.setName(student.getName());
            stud.setAge(student.getAge());
            stud.setMajor(student.getMajor());
            studentRepository.save(stud);
        }else throw new ApiException("Student not found");
    }
    public void deleteStudent(Integer id) {
        Student stud = studentRepository.findStudentById(id);
        if (stud != null) {
            studentRepository.delete(stud);
        }else throw new ApiException("Student not found");
    }

    //Create endpoint that takes student id and major and change the student major
    //(changing the major will drop all the cousres that the student attended to )

    public void changeStudentMajor(Integer Student_id, String Major) {
        Student stud = studentRepository.findStudentById(Student_id);
        Set<Course> courses = stud.getCourses();

        if (stud != null) {

            if (courses != null) {
                for (Course course : courses) {
                    course.getStudents().remove(stud);
                    courseRepository.save(course);
                }
            }else throw new ApiException("Course not found");

            stud.setMajor(Major);
            stud.setCourses(null);
            studentRepository.save(stud);
        }else throw new ApiException("Student not found");
    }
}
