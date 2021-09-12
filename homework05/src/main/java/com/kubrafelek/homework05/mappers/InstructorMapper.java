package com.kubrafelek.homework05.mappers;

import com.kubrafelek.homework05.dto.InstructorDTO;
import com.kubrafelek.homework05.model.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {

    Instructor mapFromInstructorDTOtoInstructor(InstructorDTO instructorDTO);

    InstructorDTO mapFromInstructortoInstructorDTO(Instructor instructor);

}
