package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.enums.TherapyType;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProcAndMedDAOImp implements ProcAndMedDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProcedureAndMedicinePO> getAllProceduresAndMedicines() {
        return entityManager.createQuery("select p from ProcedureAndMedicinePO p").getResultList();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProcedures(){
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.type = :type");
        query.setParameter("type", TherapyType.PROCEDURE);
        return query.getResultList();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllMedicines(){
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.type = :type");
        query.setParameter("type", TherapyType.MEDICINE);
        return query.getResultList();
    }

    @Override
    public void add(ProcedureAndMedicinePO procedureOrMedicine) {
        entityManager.persist(procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getById(Long id) {
        return entityManager.find(ProcedureAndMedicinePO.class,id);
    }

    @Override
    public void delete(ProcedureAndMedicinePO procedureOrMedicine) {
        entityManager.remove(procedureOrMedicine);
    }

    @Override
    public void update(ProcedureAndMedicinePO procedureOrMedicine) {
        entityManager.merge(procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getByName(String name){
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.name = :name");
        query.setParameter("name", name);
        return (ProcedureAndMedicinePO) query.getSingleResult();
    }
}
