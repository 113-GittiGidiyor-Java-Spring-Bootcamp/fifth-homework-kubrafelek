package com.kubrafelek.homework05.controller;

import com.kubrafelek.homework05.dto.StudentDTO;
import com.kubrafelek.homework05.model.Student;
import com.kubrafelek.homework05.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    StudentService mockStudentservice;

    @InjectMocks
    StudentController studentController;

    @Test
    void saveStudent(){
        Student student = new Student();
        Optional<Student> expected = Optional.of(student);
        Mockito.when(mockStudentservice.saveStudent(Mockito.any())).thenReturn(expected);

        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentController.saveStudent(studentDTO).getBody();

        assertAll(
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(student.getId(), actual.getId()),
                () -> assertEquals(student.getName(), actual.getName()),
                () -> assertEquals(student.getBirthdate(), actual.getBirthdate())
        );
    }
}
