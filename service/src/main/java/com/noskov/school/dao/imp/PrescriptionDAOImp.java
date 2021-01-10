package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PrescriptionPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrescriptionDAOImp implements PrescriptionDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PrescriptionPO> getAllPrescriptions() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PrescriptionPO").list();
    }

    @Override
    public void add(PrescriptionPO prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.persist("PrescriptionPO", prescription);
    }

    @Override
    public PrescriptionPO getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (PrescriptionPO) session.get(PrescriptionPO.class, id);
    }

    @Override
    public void delete(PrescriptionPO prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("PrescriptionPO", prescription);
    }

    @Override
    public void update(PrescriptionPO prescription) {
        Session session = sessionFactory.getCurrentSession();
        session.update("PrescriptionPO", prescription);
    }
}
