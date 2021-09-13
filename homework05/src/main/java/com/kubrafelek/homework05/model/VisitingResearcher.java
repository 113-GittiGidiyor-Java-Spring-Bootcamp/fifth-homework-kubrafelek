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
public class VisitingResearcher extends Instructor{

    private double hourlySalary;

    public VisitingResearcher(String name, String address, String phoneNumber, List<Course> courseList, double hourlySalary) {
        super(name, address, phoneNumber, courseList);
        this.hourlySalary = hourlySalary;
    }
}
