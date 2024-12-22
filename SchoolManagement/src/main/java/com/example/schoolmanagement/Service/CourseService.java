package com.example.schoolmanagement.Service;

import com.example.schoolmanagement.Api.ApiException;
import com.example.schoolmanagement.DTO.CourseDTO;
import com.example.schoolmanagement.DTO.StudentDTO;
import com.example.schoolmanagement.Model.Course;
import com.example.schoolmanagement.Model.Student;
import com.example.schoolmanagement.Model.Teacher;
import com.example.schoolmanagement.Repository.CourseRepository;
import com.example.schoolmanagement.Repository.StudentRepository;
import com.example.schoolmanagement.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course> findAllCourses() {return courseRepository.findAll();}
    public List<CourseDTO> findAllCoursesDTO() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> coursesDTO = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getName(),course.getTeacher().getName(), courseCountStudentById(course.getId()));
            coursesDTO.add(courseDTO);
        }
        return coursesDTO;
    }
    public Integer courseCountStudentById(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId);
        Integer count = 0;
        for (Course c : courseRepository.findAll()) {
            if (c.getId().equals(courseId)) {
                count = c.getStudents().size();
            }
        }
        return count;
    }
    public void addCourse(Integer id,Course course) {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null) {throw new ApiException("teacher not found");}

        course.setTeacher(teacher);
        courseRepository.save(course);
    }
    public void updateCourse(Integer id, Course course) {
        Course oldCourse = courseRepository.findCourseById(id);
        if (oldCourse == null) {throw new ApiException("course not found");}
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id);

        if (course == null) {throw new ApiException("course not found");}

        courseRepository.delete(course);
    }

    //Create endpoint that take course id and return the teacher name for that class
    public String courseTeacher(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {throw new ApiException("course not found");}
        return "Teacher name for "+course.getName()+" is "+course.getTeacher().getName();
    }

    public void assignCourseToStudent(Integer course_id, Integer student_id) {
        Course course = courseRepository.findCourseById(course_id);
        Student student = studentRepository.findStudentById(student_id);

        if (course == null || student == null) {throw new ApiException("course or student not found");}

        course.getStudents().add(student);
        student.getCourses().add(course);
        courseRepository.save(course);
        studentRepository.save(student);
    }

    //• Create endpoint that takes course id and return the student list
    public List<StudentDTO> getStudentByCourseId(Integer course_id) {
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {throw new ApiException("course not found");}

        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : course.getStudents()) {
            StudentDTO studentDTO = new StudentDTO(student.getName(),student.getMajor(),student.getCourses());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }
}
