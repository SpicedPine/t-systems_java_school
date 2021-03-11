package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PrescriptionDAOImp implements PrescriptionDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionDAOImp.class);

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from PrescriptionPO p where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        LOGGER.debug("deleted prescription with id = {}{", id);
    }

    @Override
    public List<PrescriptionPO> getAllPrescriptions() {
        return entityManager.createQuery("select p from PrescriptionPO p", PrescriptionPO.class).getResultList();
    }

    @Override
    public void add(PrescriptionPO prescription) {
        entityManager.persist(prescription);

        LOGGER.debug("added prescription: {}", prescription.getFormedPrescription());
    }

    @Override
    public PrescriptionPO getById(Long id) {
        PrescriptionPO prescription = entityManager.find(PrescriptionPO.class,id);
        if(prescription != null){
            LOGGER.debug("returned prescription with id = {}", id);

            return prescription;
        } else{
            LOGGER.error("Couldn't find prescription with id = {}", id);
            throw new NullPointerException("from getById in PrescriptionServiceImp");
        }
    }

    @Override
    public void delete(PrescriptionPO prescription) {
        entityManager.remove(prescription);
        LOGGER.debug("deleted prescription: {}", prescription.getFormedPrescription());
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
        LOGGER.debug("returne prescriptions for patient {}", patient.getLastName());
        return query.getResultList();
    }

    @Override
    public void deletePrescriptionsByPatient(PatientPO patient) {
        Query query = entityManager.createQuery("delete from PrescriptionPO p " +
                " where p.patient = :patient");
        query.setParameter("patient", patient);
        query.executeUpdate();

        LOGGER.debug("deleted prescriptions for patient {}", patient.getLastName());
    }
}
