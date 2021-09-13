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
    public Optional<PermanentInstructor> salaryChanging(long instructorId, String percentageName, double percentageValue) {

        Optional<PermanentInstructor> permanentInstructor = ;
        double currentSalary = permanentInstructor.get().getFixedSalary();

        if (percentageName.equals(PercentageSign.MINUS.getPercentageName())) {

            permanentInstructor.get().setFixedSalary(permanentInstructor.get().getFixedSalary() - (permanentInstructor.get().getFixedSalary() * (percentageValue / 100)));
            this.saveTransactionToDatabase(permanentInstructor.get(), percentageValue, percentageName, TransactionType.SALARY_DECREASED);

        } else if (percentageName.equals(PercentageSign.PLUS.getPercentageName())) {

            permanentInstructor.get().setFixedSalary(permanentInstructor.get().getFixedSalary() + (permanentInstructor.get().getFixedSalary() * (percentageValue / 100)));
            this.saveTransactionToDatabase(permanentInstructor.get(), percentageValue, percentageName, TransactionType.SALARY_INCREASED);

        } else {
            throw new BadRequestException("400 STATUS");
        }
        instructorRepository.save(permanentInstructor.get());
        return permanentInstructor;
    }

    private void saveTransactionToDatabase(PermanentInstructor permanentInstructor, double percentageValue, String percentageName, TransactionType transactionType) {
        TransactionLogger transactionLogger = new TransactionLogger();

        if (transactionType.equals(TransactionType.SALARY_DECREASED)) {
            transactionLogger.setPreviousSalary(permanentInstructor.getFixedSalary() + (permanentInstructor.getFixedSalary() * (percentageValue / 100)));
        } else if (transactionType.equals(TransactionType.SALARY_INCREASED)) {
            transactionLogger.setPreviousSalary(permanentInstructor.getFixedSalary() - (permanentInstructor.getFixedSalary() * (percentageValue / 100)));
        }

        transactionLogger.setNewSalary(permanentInstructor.getFixedSalary());
        transactionLogger.setTransactionDataTime(LocalDateTime.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setTransactionType(transactionType);

        this.transactionLoggerRepository.save(transactionLogger);
    }

    private Optional<PermanentInstructor> getSalary(long instructorId) {
        return instructorRepository.findInstructorSalary(instructorId);
    }
}
