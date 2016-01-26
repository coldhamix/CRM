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
public class VisitManager implements Manager<Visit, Long>, AutoCloseable {
    private final Session session;

    public VisitManager() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Visit> list() {
        session.beginTransaction();
        List<Visit> result = session.createQuery("from Visit").list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void create(Visit visit) {
        session.beginTransaction();
        session.save(visit);
        session.getTransaction().commit();
    }

    @Override
    public Visit findById(Long id) {
        session.beginTransaction();
        Visit visit = (Visit) session.get(Visit.class, id);
        session.getTransaction().commit();
        return visit;
    }

    @Override
    public void update(Visit visit) {
        session.beginTransaction();
        session.update(visit);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Visit visit) {
        session.beginTransaction();
        session.delete(visit);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        session.beginTransaction();
        session.delete(
                session.get(Visit.class, id)
        );
        session.getTransaction().commit();
    }

    @Override
    public void close() throws Exception {
        session.close();
    }
}
