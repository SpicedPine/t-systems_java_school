package com.noskov.school.dao.imp;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.dao.api.PatientDAO;
import javax.persistence.Query;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class PatientDAOImp implements PatientDAO {
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    /*private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }*/

    @Override
    public List<PatientPO> getAllPatients() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<PatientPO> patientList = entityManager.createQuery("select p from PatientPO p").getResultList();
        return patientList;
    }

    @Override
    public PatientPO getById(Long id) throws NullPointerException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from PatientPO p join fetch p.prescriptionList where p.id = :id");
        query.setParameter("id", id);
        PatientPO patient = (PatientPO) query.getSingleResult();
        if(patient != null){
            return patient;
        } else{
            throw new NullPointerException("from getById in PatientServiceImp");
        }
    }

    @Override
    public void delete(PatientPO patient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(patient);
    }

    @Override
    public void add(PatientPO patient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(patient);
    }

    @Override
    public void update(PatientPO patient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.refresh(patient);
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        PatientPO patient = getById(id);
        entityManager.remove(patient);
    }
}
