package com.kubrafelek.homework05.controller;

import com.kubrafelek.homework05.dto.InstructorDTO;
import com.kubrafelek.homework05.dto.PermanentInstructorDTO;
import com.kubrafelek.homework05.dto.VisitingResearcherDTO;
import com.kubrafelek.homework05.model.Instructor;
import com.kubrafelek.homework05.model.PermanentInstructor;
import com.kubrafelek.homework05.model.VisitingResearcher;
import com.kubrafelek.homework05.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    /**
     * The method list all instructors
     **/
    @GetMapping("/list-instructors")
    public ResponseEntity<List<Instructor>> findAll() {
        return new ResponseEntity(instructorService.findAll(), HttpStatus.OK);
    }

    //valid anotasyonu eklenmedi

    /**
     * The method add the new instructor.
     */
 /*   @PostMapping("/add-instructor")
    public ResponseEntity<Instructor> addInstructor(@RequestBody InstructorDTO instructorDTO) {

        Optional<Instructor> resultOptional = instructorService.saveInstructor(instructorDTO);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
    @PostMapping("/add-permanent-instructor")
    public ResponseEntity<PermanentInstructor> addPermanentInstructor(@RequestBody PermanentInstructorDTO permanentInstructorDTO) {

        Optional<PermanentInstructor> resultOptional = instructorService.savePermanentInstructor(permanentInstructorDTO);

        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-visiting-researcher")
    public ResponseEntity<VisitingResearcher> addVisitingResearcher(@RequestBody VisitingResearcherDTO visitingResearcherDTO) {

        Optional<VisitingResearcher> resultOptional = instructorService.saveVisitingResearcher(visitingResearcherDTO);

        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * The method find exist instructor by using instructor id.
     *
     * @param id The instructor id
     */
    @GetMapping("/findInstructorById/{id}")
    public ResponseEntity<Instructor> findInstructorById(@PathVariable long id) {
        return new ResponseEntity(instructorService.findInstructorById(id), HttpStatus.OK);
    }

    /**
     * The method updates exist instructor data by using id.
     *
     * @param instructorDTO The instructor object
     * @param id            The instructor id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Instructor> updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable int id) {
        return new ResponseEntity(instructorService.updateInstructor(instructorDTO, id), HttpStatus.ACCEPTED);
    }

    /**
     * The method deletes exist instructor by using id.
     *
     * @param id The instructor id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Instructor> deleteIntructorById(@PathVariable long id) {
        return new ResponseEntity(instructorService.deleteInstructorById(id), HttpStatus.ACCEPTED);
    }

    /**
     * The method deletes exist instructor by using id.
     *
     * @param instructorId    The instructor id
     * @param percentageName  The percentage name
     * @param percentageValue The percentage value
     */
    @PutMapping("salaryChangingProcess/{instructorId}/{percentageName}/{percentageValue}")
    public ResponseEntity<PermanentInstructor> salaryChanging(@PathVariable long instructorId,
                                                              @PathVariable("percentageName") String percentageName,
                                                              @PathVariable double percentageValue) {
        Optional<PermanentInstructor> resultOptional = instructorService.salaryChanging(instructorId, percentageName, percentageValue);

        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
