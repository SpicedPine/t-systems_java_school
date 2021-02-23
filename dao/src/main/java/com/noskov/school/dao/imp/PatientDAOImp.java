package com.noskov.school.dao.imp;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.dao.api.PatientDAO;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PatientDAOImp implements PatientDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PatientPO> getAllPatients() {
        List<PatientPO> patientList = entityManager.createQuery("select p from PatientPO p",PatientPO.class).getResultList();
        return patientList;
    }

    @Override
    public PatientPO getById(Long id) throws NullPointerException {
        Query query = entityManager.createQuery("select p from PatientPO as p where p.id = :id");
        query.setParameter("id", id);
        PatientPO patient = (PatientPO) query.getSingleResult();
        if(patient != null){
            return patient;
        } else{
            throw new RuntimeException("from getById in PatientServiceImp");
        }
    }

    @Override
    public void delete(PatientPO patient) {
        entityManager.remove(patient);
    }

    @Override
    public void add(PatientPO patient) {
        entityManager.persist(patient);
    }

    @Override
    public void update(PatientPO patient) {
        entityManager.merge(patient);
    }

    @Override
    public void deleteById(Long id) {
        PatientPO patient = getById(id);
        entityManager.remove(patient);
    }

    @Override
    public PatientPO getBySocialNumber(int socialNumber) {
        Query query = entityManager.createQuery("select p from PatientPO as p where p.socialNumber = :socialNumber");
        query.setParameter("socialNumber", socialNumber);
        try{
            PatientPO patientPO = (PatientPO) query.getSingleResult();
            return patientPO;
        } catch (NoResultException e){
            return null;
        }
    }
}
