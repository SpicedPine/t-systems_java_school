package com.noskov.school.dao.imp;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.dao.api.PatientDAO;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PatientDAOImp implements PatientDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PatientPO> getAllPatients() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select PatientPO from PatientPO").list();
    }

    @Override
    public PatientPO getById(Long id) throws NullPointerException {
        Session session = sessionFactory.getCurrentSession();
        PatientPO patient = session.get(PatientPO.class, id);
        if(patient != null){
            return patient;
        } else{
            throw new NullPointerException("from getById in PatientServiceImp");
        }
    }

    @Override
    public void delete(PatientPO patient) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("PatientPO", patient);
    }

    @Override
    public void add(PatientPO patient) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patient);
    }

    @Override
    public void update(PatientPO patient) {
        Session session = sessionFactory.getCurrentSession();
        session.update("PatientPO", patient);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PatientPO where id = :id").setParameter("id",id);
        query.executeUpdate();
    }
}
