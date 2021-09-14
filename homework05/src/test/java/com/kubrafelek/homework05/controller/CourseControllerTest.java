package com.kubrafelek.homework05.controller;

import com.kubrafelek.homework05.dto.CourseDTO;
import com.kubrafelek.homework05.model.Course;
import com.kubrafelek.homework05.service.CourseService;
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
public class CourseControllerTest {

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;

    @Test
    void saveCourse() {
        Course course = new Course();
        Optional<Course> expected = Optional.of(course);
        Mockito.when(courseService.saveCourse(Mockito.any())).thenReturn(expected);

        CourseDTO courseDTO = new CourseDTO();
        Course actual = (Course) this.courseController.addCourse(courseDTO).getBody();

        assertAll(
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(course.getId(), actual.getId()),
                () -> assertEquals(course.getCourseCode(), actual.getCourseCode()),
                () -> assertEquals(course.getCreditScore(), actual.getCreditScore()),
                () -> assertEquals(course.getCourseName(), actual.getCourseName())
        );
    }
}
