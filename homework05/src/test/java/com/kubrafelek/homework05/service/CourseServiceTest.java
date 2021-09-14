package com.kubrafelek.homework05.service;

import com.kubrafelek.homework05.dto.CourseDTO;
import com.kubrafelek.homework05.exceptions.CourseIsAlreadyExistException;
import com.kubrafelek.homework05.mappers.CourseMapper;
import com.kubrafelek.homework05.model.Course;
import com.kubrafelek.homework05.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    CourseRepository mockCourseRepository;

    @Mock
    CourseMapper mockCourseMapper;

    @InjectMocks
    CourseService courseService;

    @Test
    void saveCourse() {
        Course course = new Course();
        when(mockCourseRepository.selectExistsCourseCode(anyInt())).thenReturn(Boolean.FALSE);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(course);
        when(mockCourseRepository.save(any())).thenReturn(course);


        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseService.saveCourse(courseDTO).get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(course, actual),
                () -> assertEquals(course.getId(), actual.getId())
        );
    }

    @Test
    void courseExist() {
        Course course = new Course();
        when(mockCourseRepository.findCourseByCourseCode(anyInt())).thenReturn(course);

        CourseDTO dto = new CourseDTO();
        Executable executable = () -> this.courseService.saveCourse(dto).get();

        assertThrows(CourseIsAlreadyExistException.class, executable);
    }
}
