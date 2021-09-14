package com.kubrafelek.homework05.repository;

import com.kubrafelek.homework05.model.TransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionLoggerRepository extends PagingAndSortingRepository<TransactionLogger, Long> {
    @Query("SELECT w FROM TransactionLogger w WHERE w.transactionDataTime= ?1")
    Page<List<TransactionLogger>> findAllTransactionByTransactionDate(LocalDate transactionDate, Pageable pageable);

    @Query("SELECT w FROM TransactionLogger w WHERE w.transactionDataTime= ?1")
    Page<List<TransactionLogger>> findAllTransactionByTransactionId(long instructorId, Pageable pageable);
}
