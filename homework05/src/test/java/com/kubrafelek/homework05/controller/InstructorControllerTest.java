package com.kubrafelek.homework05.controller;

import com.kubrafelek.homework05.controller.InstructorController;
import com.kubrafelek.homework05.dto.InstructorDTO;
import com.kubrafelek.homework05.dto.PermanentInstructorDTO;
import com.kubrafelek.homework05.dto.VisitingResearcherDTO;
import com.kubrafelek.homework05.model.Instructor;
import com.kubrafelek.homework05.model.PermanentInstructor;
import com.kubrafelek.homework05.model.VisitingResearcher;
import com.kubrafelek.homework05.service.InstructorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class InstructorControllerTest {

    @Mock
    InstructorService mockInstructorService;

    @InjectMocks
    InstructorController instructorController;

    @Test
    void savePermanentInstructor() {
        PermanentInstructor permanentInstructor = new PermanentInstructor();
        Optional<PermanentInstructor> expected = Optional.of(permanentInstructor);
        Mockito.when(mockInstructorService.savePermanentInstructor(Mockito.any())).thenReturn(expected);

        PermanentInstructorDTO permanentInstructorDTO = new PermanentInstructorDTO();
        PermanentInstructor actual = this.instructorController.addPermanentInstructor(permanentInstructorDTO).getBody();

        assertAll(
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(permanentInstructor.getId(), actual.getId()),
                () -> assertEquals(permanentInstructor.getFixedSalary(), actual.getFixedSalary())
        );
    }

    @Test
    void saveVisitingResearcher() {
        VisitingResearcher visitingResearcher = new VisitingResearcher();
        Optional<VisitingResearcher> expected = Optional.of(visitingResearcher);
        Mockito.when(mockInstructorService.saveVisitingResearcher(Mockito.any())).thenReturn(expected);

        VisitingResearcherDTO visitingResearcherDTO = new VisitingResearcherDTO();
        VisitingResearcher actual = this.instructorController.addVisitingResearcher(visitingResearcherDTO).getBody();

        assertAll(
                () -> assertEquals(expected.get(), actual),
                () -> assertEquals(visitingResearcher.getId(), actual.getId()),
                () -> assertEquals(visitingResearcher.getHourlySalary(), actual.getHourlySalary())
        );
    }
}
