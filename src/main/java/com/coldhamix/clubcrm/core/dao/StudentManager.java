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
public class StudentManager {
    // TODO: Change session lifetime

    public static List<Student> list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Student> result = session.createQuery("from Student").list();
        session.getTransaction().commit();
        return result;
    }

    public static void save(Student student) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }

    public static Student get(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    public static void update(Student student) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
    }

    public static void delete(Student student) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
    }
}
