package com.kubrafelek.homework05.mappers;

import com.kubrafelek.homework05.dto.PermanentInstructorDTO;
import com.kubrafelek.homework05.model.PermanentInstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PermanentInstructorMapper {

    public abstract PermanentInstructor mapPermanentInstructorDTOToPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);
    public abstract PermanentInstructorDTO mapPermanentInstructorToPermanentInstructorDTO(PermanentInstructor permanentInstructor);

}
