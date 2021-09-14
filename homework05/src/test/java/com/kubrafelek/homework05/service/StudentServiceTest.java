package com.kubrafelek.homework05.service;

import com.kubrafelek.homework05.dto.StudentDTO;
import com.kubrafelek.homework05.mappers.StudentMapper;
import com.kubrafelek.homework05.model.Student;
import com.kubrafelek.homework05.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository mockStudentRepository;

    @Mock
    StudentMapper mockStudentMapper;

    @InjectMocks
    StudentService studentService;

    @Test
    void saveCourse() {
        Student student = new Student();
        when(mockStudentMapper.mapFromStudentDTOtoStudent(any())).thenReturn(student);
        when(mockStudentRepository.save(any())).thenReturn(student);


        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentService.saveStudent(studentDTO).get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(student, actual),
                () -> assertEquals(student.getName(), actual.getName())
        );
    }
}
