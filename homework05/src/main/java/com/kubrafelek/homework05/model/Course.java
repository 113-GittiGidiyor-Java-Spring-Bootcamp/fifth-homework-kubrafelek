package com.kubrafelek.homework05.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course extends AbstractBaseEntity{

    private String courseName;
    private int courseCode;
    private int creditScore;

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;

    @ManyToMany()
    private List<Student> studentList = new ArrayList<>();

}
