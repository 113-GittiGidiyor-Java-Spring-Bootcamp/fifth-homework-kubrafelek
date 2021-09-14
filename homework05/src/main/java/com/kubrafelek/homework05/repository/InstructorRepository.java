package com.kubrafelek.homework05.repository;

import com.kubrafelek.homework05.model.Instructor;
import com.kubrafelek.homework05.model.PermanentInstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN TRUE ELSE FALSE END FROM Instructor i WHERE i.phoneNumber = ?1")
    boolean selectExistsPhoneNumber(String phoneNumber);

    @Query("select p.fixedSalary from PermanentInstructor p where p.id = :instructorId")
    double findPermanentInstructorSalary(long instructorId);

    @Query("select v.hourlySalary from VisitingResearcher v where v.id = :instructorId")
    double findVisitingResearcherSalary(long instructorId);
}
