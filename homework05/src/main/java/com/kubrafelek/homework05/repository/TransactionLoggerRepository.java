package com.kubrafelek.homework05.repository;

import com.kubrafelek.homework05.model.TransactionLogger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLoggerRepository extends PagingAndSortingRepository<TransactionLogger, Long> {
}
