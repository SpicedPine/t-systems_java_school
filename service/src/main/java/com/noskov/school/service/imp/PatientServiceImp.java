package com.noskov.school.service.imp;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.service.api.PatientService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<PatientPO> getAllPatients() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PatientPO").list();
    }

    @Override
    public void add(PatientPO patient) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patient);
    }
}