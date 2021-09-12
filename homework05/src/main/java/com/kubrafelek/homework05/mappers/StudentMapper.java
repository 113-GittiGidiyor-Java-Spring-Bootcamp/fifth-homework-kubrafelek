package com.kubrafelek.homework05.mappers;

import com.kubrafelek.homework05.dto.StudentDTO;
import com.kubrafelek.homework05.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    public abstract Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    public abstract Student mapFromStudenttoStudentDTO(Student student);
}
