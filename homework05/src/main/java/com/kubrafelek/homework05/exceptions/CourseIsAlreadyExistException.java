package com.kubrafelek.homework05.exceptions;

public class CourseIsAlreadyExistException extends RuntimeException{
    public CourseIsAlreadyExistException(String message) {
        super(message);
    }
}
