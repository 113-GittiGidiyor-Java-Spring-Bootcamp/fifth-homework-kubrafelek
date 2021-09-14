package com.kubrafelek.homework05.service;

import com.kubrafelek.homework05.dto.InstructorDTO;
import com.kubrafelek.homework05.dto.PermanentInstructorDTO;
import com.kubrafelek.homework05.dto.VisitingResearcherDTO;
import com.kubrafelek.homework05.exceptions.BadRequestException;
import com.kubrafelek.homework05.exceptions.InstructorIsAlreadyExistException;
import com.kubrafelek.homework05.mappers.InstructorMapper;
import com.kubrafelek.homework05.mappers.PermanentInstructorMapper;
import com.kubrafelek.homework05.mappers.VisitingResearcherMapper;
import com.kubrafelek.homework05.model.Instructor;
import com.kubrafelek.homework05.model.PermanentInstructor;
import com.kubrafelek.homework05.model.TransactionLogger;
import com.kubrafelek.homework05.model.VisitingResearcher;
import com.kubrafelek.homework05.model.enumeration.PercentageSign;
import com.kubrafelek.homework05.model.enumeration.TransactionType;
import com.kubrafelek.homework05.repository.InstructorRepository;
import com.kubrafelek.homework05.repository.TransactionLoggerRepository;
import com.kubrafelek.homework05.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    @Autowired
    private final PermanentInstructorMapper permanentInstructorMapper;

    @Autowired
    private final VisitingResearcherMapper visitingResearcherMapper;

    @Autowired
    private ClientRequestInfo clientRequestInfo;

    @Autowired
    private TransactionLoggerRepository transactionLoggerRepository;

/*    @Transactional
    public Optional<Instructor> saveInstructor(InstructorDTO instructorDTO) {

        boolean isExists = instructorRepository.selectExistsPhoneNumber(instructorDTO.getPhoneNumber());
        if (isExists) {
            throw new InstructorIsAlreadyExistException("Instructor phone number " + instructorDTO.getPhoneNumber() + " is already exists !");
        }

        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        return Optional.of(instructorRepository.save(instructor));
    }*/

    @Transactional
    public Optional<PermanentInstructor> savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO) {

        boolean isExists = instructorRepository.selectExistsPhoneNumber(permanentInstructorDTO.getPhoneNumber());
        if (isExists) {
            throw new InstructorIsAlreadyExistException("Permanent Instructor phone number " + permanentInstructorDTO.getPhoneNumber() + " is already exists !");
        }
        PermanentInstructor instructor = permanentInstructorMapper.mapPermanentInstructorDTOToPermanentInstructor(permanentInstructorDTO);
        return Optional.of(instructorRepository.save(instructor));
    }

    @Transactional
    public Optional<VisitingResearcher> saveVisitingResearcher(VisitingResearcherDTO visitingReseracherDTO) {

        boolean isExists = instructorRepository.selectExistsPhoneNumber(visitingReseracherDTO.getPhoneNumber());
        if (isExists) {
            throw new InstructorIsAlreadyExistException("Visiting Researcher phone number " + visitingReseracherDTO.getPhoneNumber() + " is already exists !");
        }
        VisitingResearcher instructor = visitingResearcherMapper.mapVisitingResearcherDTOToVisitingResearcher(visitingReseracherDTO);
        return Optional.of(instructorRepository.save(instructor));
    }


    @Transactional(readOnly = true)
    public List<Instructor> findAll() {
        List<Instructor> instructorList = new ArrayList<>();
        Iterable<Instructor> instructorIterable = instructorRepository.findAll();
        instructorIterable.iterator().forEachRemaining(instructorList::add);
        return instructorList;
    }

    @Transactional
    public Instructor findInstructorById(long id) {
        return instructorRepository.findById(id).get();
    }

    @Transactional
    public Instructor updateInstructor(InstructorDTO instructorDTO, long id) {
        Instructor findInstructor = findInstructorById(id);
        findInstructor.setName(instructorDTO.getName());
        findInstructor.setAddress(instructorDTO.getAddress());
        findInstructor.setPhoneNumber(instructorDTO.getPhoneNumber());
        return instructorRepository.save(findInstructor);
    }

    public String deleteInstructorById(long id) {
        instructorRepository.deleteById(id);
        return "Instructor id => " + id + " Deleted....";
    }

    @Transactional
    public Optional<PermanentInstructor> salaryChangingPermanentInstructor(long instructorId, String percentageName, double percentageValue) {

        double currentSalary = instructorRepository.findPermanentInstructorSalary(instructorId);
        TransactionType transactionType = null;
        double newSalary = 0.0;

        if (percentageName.equals(PercentageSign.MINUS.getPercentageName())) {
            newSalary = currentSalary - (currentSalary * (percentageValue / 100));
            transactionType = TransactionType.SALARY_DECREASED;
        } else if (percentageName.equals(PercentageSign.PLUS.getPercentageName())) {
            newSalary = currentSalary + (currentSalary * (percentageValue / 100));
            transactionType = TransactionType.SALARY_INCREASED;
        }

        PermanentInstructor permanentInstructor = (PermanentInstructor) findInstructorById(instructorId);
        permanentInstructor.setFixedSalary(newSalary);
        instructorRepository.save(permanentInstructor);

        this.saveTransactionToDatabase(instructorId, percentageValue, newSalary, currentSalary, transactionType);
        return Optional.of(permanentInstructor);
    }

    @Transactional
    public Optional<VisitingResearcher> salaryChangingVisitingResearcher(long instructorId, String percentageName, double percentageValue) {

        double currentSalary = instructorRepository.findVisitingResearcherSalary(instructorId);
        TransactionType transactionType = null;
        double newSalary = 0.0;

        if (percentageName.equals(PercentageSign.MINUS.getPercentageName())) {
            newSalary = currentSalary - (currentSalary * (percentageValue / 100));
            transactionType = TransactionType.SALARY_DECREASED;
        } else if (percentageName.equals(PercentageSign.PLUS.getPercentageName())) {
            newSalary = currentSalary + (currentSalary * (percentageValue / 100));
            transactionType = TransactionType.SALARY_INCREASED;
        }

        VisitingResearcher visitingResearcher = (VisitingResearcher) findInstructorById(instructorId);
        visitingResearcher.setHourlySalary(newSalary);
        instructorRepository.save(visitingResearcher);
        this.saveTransactionToDatabase(instructorId, percentageValue, newSalary, currentSalary, transactionType);
        return Optional.of(visitingResearcher);
    }

    private void saveTransactionToDatabase(long instructorId, double percentageValue, double newSalary, double previousSalary, TransactionType transactionType) {
        TransactionLogger transactionLogger = new TransactionLogger();

        transactionLogger.setInstructorId(instructorId);
        transactionLogger.setPercentageValue(percentageValue);

        if (transactionType.equals(TransactionType.SALARY_DECREASED)) {
            transactionLogger.setPreviousSalary(previousSalary);
        } else if (transactionType.equals(TransactionType.SALARY_INCREASED)) {
            transactionLogger.setPreviousSalary(previousSalary);
        }

        transactionLogger.setNewSalary(newSalary);
        transactionLogger.setTransactionDataTime(LocalDateTime.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setTransactionType(transactionType);

        this.transactionLoggerRepository.save(transactionLogger);
    }

}
