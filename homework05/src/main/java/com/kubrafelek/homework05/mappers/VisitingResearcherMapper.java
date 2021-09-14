package com.kubrafelek.homework05.mappers;

import com.kubrafelek.homework05.dto.VisitingResearcherDTO;
import com.kubrafelek.homework05.model.VisitingResearcher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class VisitingResearcherMapper {

    public abstract VisitingResearcher mapVisitingResearcherDTOToVisitingResearcher(VisitingResearcherDTO visitingReseracherDTO);

    public abstract VisitingResearcherDTO mapVisitingResearcherToVisitingResearcherDTO(VisitingResearcher visitingReseracher);
}
