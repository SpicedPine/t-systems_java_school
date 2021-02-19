package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PrescriptionDAOImp implements PrescriptionDAO {
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from PrescriptionPO p where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<PrescriptionPO> getAllPrescriptions() {
        return entityManager.createQuery("select p from PrescriptionPO p", PrescriptionPO.class).getResultList();
    }

    @Override
    public void add(PrescriptionPO prescription) {
        entityManager.persist(prescription);
    }

    @Override
    public PrescriptionPO getById(Long id) {
        PrescriptionPO prescription = entityManager.find(PrescriptionPO.class,id);
        if(prescription != null){
            return prescription;
        } else{
            throw new NullPointerException("from getById in PrescriptionServiceImp");
        }
    }

    @Override
    public void delete(PrescriptionPO prescription) {
        entityManager.remove(prescription);
    }

    @Override
    public void update(PrescriptionPO prescription) {
        entityManager.merge(prescription);
    }

    @Override
    public List<PrescriptionPO> getPrescriptionsByPatient(PatientPO patient){
        Query query = entityManager.createQuery("select p from PrescriptionPO p " +
                "join fetch p.prescriptionType where p.patient = :patient");
        query.setParameter("patient", patient);
        return query.getResultList();
    }
}
