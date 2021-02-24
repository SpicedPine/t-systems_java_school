package com.noskov.school.dao.imp;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.dao.api.PatientDAO;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PatientDAOImp implements PatientDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientDAOImp.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PatientPO> getAllPatients() {
        List<PatientPO> patientList = entityManager.createQuery("select p from PatientPO p",PatientPO.class).getResultList();

        LOGGER.debug("returned all patients");

        return patientList;
    }

    @Override
    public PatientPO getById(Long id) throws NullPointerException {
        Query query = entityManager.createQuery("select p from PatientPO as p where p.id = :id");
        query.setParameter("id", id);
        try{
            PatientPO patient = (PatientPO) query.getSingleResult();
            LOGGER.debug("returned patient with id={}", id);
            return patient;
        } catch (NoResultException exception){
            LOGGER.error("couldn't found patient with id = {}", id);
            throw new RuntimeException("from getById in PatientServiceImp");
        }
    }

    @Override
    public void delete(PatientPO patient) {
        entityManager.remove(patient);

        LOGGER.debug("removed patient {}", patient.getLastName());
    }

    @Override
    public void add(PatientPO patient) {
        entityManager.persist(patient);

        LOGGER.debug("persist patient {}", patient.getLastName());
    }

    @Override
    public void update(PatientPO patient) {
        entityManager.merge(patient);

        LOGGER.debug("update patient {}", patient.getLastName());
    }

    @Override
    public void deleteById(Long id) {
        PatientPO patient = getById(id);
        entityManager.remove(patient);

        LOGGER.debug("persist patient with id = {}", id);
    }

    @Override
    public PatientPO getBySocialNumber(int socialNumber) {
        Query query = entityManager.createQuery("select p from PatientPO as p where p.socialNumber = :socialNumber");
        query.setParameter("socialNumber", socialNumber);
        try{
            PatientPO patientPO = (PatientPO) query.getSingleResult();
            LOGGER.debug("return patient with social number = {}", socialNumber);
            return patientPO;
        } catch (NoResultException e){
            LOGGER.info("couldn't find patient with social number = {}", socialNumber);
            return null;
        }
    }
}
