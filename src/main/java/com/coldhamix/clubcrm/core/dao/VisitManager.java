package com.coldhamix.clubcrm.core.dao;

import com.coldhamix.clubcrm.core.domain.Visit;
import com.coldhamix.clubcrm.core.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Copyright 2016 (c) ClubCRM.
 * Author: Vadim A. Khamzin.
 * Created on: 24-01-2016
 */
public class VisitManager {
    // TODO: Change session lifetime

    public static List<Visit> list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Visit> result = session.createQuery("from Visit").list();
        session.getTransaction().commit();
        return result;
    }

    public static void save(Visit visit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(visit);
        session.getTransaction().commit();
    }

    public static Visit get(Long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Visit visit = (Visit) session.get(Visit.class, id);
        session.getTransaction().commit();
        return visit;
    }

    public static void update(Visit visit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(visit);
        session.getTransaction().commit();
    }

    public static void delete(Visit visit) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(visit);
        session.getTransaction().commit();
    }
}
