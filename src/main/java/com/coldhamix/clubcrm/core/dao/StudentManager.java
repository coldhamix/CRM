package com.coldhamix.clubcrm.core.dao;

import com.coldhamix.clubcrm.core.domain.Student;
import com.coldhamix.clubcrm.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 23-01-2016
 */
public class StudentManager implements Manager<Student, Long>, AutoCloseable {
    private final Session session;

    public StudentManager() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Student> list() {
        session.beginTransaction();
        List<Student> result = session.createQuery("from Student").list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void create(Student student) {
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }

    @Override
    public Student findById(Long id) {
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public void update(Student student) {
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Student student) {
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        session.beginTransaction();
        session.delete(
                session.get(Student.class, id)
        );
        session.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        session.close();
    }
}
