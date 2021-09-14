package com.kubrafelek.homework05.util;

import com.kubrafelek.homework05.exceptions.TransactionDateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateDateUtil {

    public static void validateTransactionDate(String transactionDate, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(transactionDate, formatter);
        } catch (DateTimeParseException e) {
            throw new TransactionDateTimeParseException(ErrorMessageConstants.DATE_FORMAT_WRONG + transactionDate);
        }

    }
}
