package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PrescriptionPO;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PrescriptionDAOImp implements PrescriptionDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PrescriptionPO as p where p.id = :id").setParameter("id", id);
        query.executeUpdate();
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
        PrescriptionPO prescription = session.get(PrescriptionPO.class, id);
        if(prescription != null){
            return prescription;
        } else{
            throw new NullPointerException("from getById in PrescriptionServiceImp");
        }
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
