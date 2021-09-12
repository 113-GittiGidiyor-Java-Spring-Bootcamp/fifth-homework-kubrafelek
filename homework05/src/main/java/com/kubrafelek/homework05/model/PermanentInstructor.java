package com.kubrafelek.homework05.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PermanentInstructor extends Instructor{

    private double fixedSalary;
}
