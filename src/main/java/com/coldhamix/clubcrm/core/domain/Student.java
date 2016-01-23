package com.coldhamix.clubcrm.core.domain;

import com.coldhamix.clubcrm.core.dao.StudentManager;
import com.coldhamix.clubcrm.core.dao.VisitManager;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 23-01-2016
 */

@Entity
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date birthday;

    // TODO: Workaround because of too short session lifetime. Eager must be changed back to lazy
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Visit> visits;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    public void addVisit(Visit v) {
        visits.add(v);
        v.setStudent(this);

        StudentManager.update(this);
        VisitManager.update(v);
    }

    public void removeVisit(Visit v) {
        if (visits.contains(v)) {
            visits.remove(v);
            v.setStudent(null);

            StudentManager.update(this);
            VisitManager.update(v);
        }
    }
}
