package com.kubrafelek.homework05.repository;

import com.kubrafelek.homework05.model.ExceptionsLogger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLoggerRepository extends PagingAndSortingRepository<ExceptionsLogger, Long> {
}

