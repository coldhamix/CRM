package com.coldhamix.clubcrm.core.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 23-01-2016
 */

@Entity
public class Visit {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ADDITIONAL = 1;
    public static final int TYPE_INDIVIDUAL = 2;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private Date visitDate;
    private int typeOfVisit;

    @ManyToOne
    private Student student;

    public Visit() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public int getTypeOfVisit() {
        return typeOfVisit;
    }

    public void setTypeOfVisit(int typeOfVisit) {
        this.typeOfVisit = typeOfVisit;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
