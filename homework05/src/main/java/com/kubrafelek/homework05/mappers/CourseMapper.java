package com.kubrafelek.homework05.mappers;

import com.kubrafelek.homework05.dto.CourseDTO;
import com.kubrafelek.homework05.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);
}
