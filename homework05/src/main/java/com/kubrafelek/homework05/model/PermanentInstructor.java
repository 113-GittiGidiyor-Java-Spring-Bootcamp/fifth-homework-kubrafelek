package com.kubrafelek.homework05.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PermanentInstructor extends Instructor{

    private double fixedSalary;

    public PermanentInstructor(String name, String address, String phoneNumber, List<Course> courseList, double fixedSalary) {
        super(name, address, phoneNumber, courseList);
        this.fixedSalary = fixedSalary;
    }
}
