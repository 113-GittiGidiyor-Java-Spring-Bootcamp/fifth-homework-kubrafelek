package com.kubrafelek.homework05.service;

import com.kubrafelek.homework05.dto.CourseDTO;
import com.kubrafelek.homework05.dto.InstructorDTO;
import com.kubrafelek.homework05.dto.PermanentInstructorDTO;
import com.kubrafelek.homework05.exceptions.CourseIsAlreadyExistException;
import com.kubrafelek.homework05.exceptions.InstructorIsAlreadyExistException;
import com.kubrafelek.homework05.mappers.InstructorMapper;
import com.kubrafelek.homework05.model.Course;
import com.kubrafelek.homework05.model.Instructor;
import com.kubrafelek.homework05.model.PermanentInstructor;
import com.kubrafelek.homework05.repository.InstructorRepository;
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
public class InstructorServiceTest {

    @Mock
    InstructorRepository mockInstructorRepository;

    @Mock
    InstructorMapper mockInstructorMapper;

    @InjectMocks
    InstructorService instructorService;

    @Test
    void saveInstructor() {
        PermanentInstructor permanentInstructor = new PermanentInstructor();
        when(mockInstructorRepository.selectExistsPhoneNumber(anyString())).thenReturn(Boolean.FALSE);
        when(mockInstructorMapper.mapFromInstructorDTOtoInstructor(any())).thenReturn(permanentInstructor);
        when(mockInstructorRepository.save(any())).thenReturn(permanentInstructor);


        PermanentInstructorDTO permanentInstructorDTO = new PermanentInstructorDTO();
        Instructor actual = this.instructorService.savePermanentInstructor(permanentInstructorDTO).get();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(permanentInstructor, actual),
                () -> assertEquals(permanentInstructor.getId(), actual.getId())
        );
    }

    @Test
    void instructorExist() {
        // given
        Instructor instructor = new Instructor();
        when(mockInstructorRepository.selectExistsPhoneNumber(anyString())).thenReturn(Boolean.TRUE);

        // when
        PermanentInstructorDTO dto = new PermanentInstructorDTO();
        Executable executable = () -> this.instructorService.savePermanentInstructor(dto).get();

        // then
        assertThrows(InstructorIsAlreadyExistException.class, executable);
    }
}
