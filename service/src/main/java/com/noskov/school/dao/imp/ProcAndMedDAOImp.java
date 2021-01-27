package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.enums.TherapyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProcAndMedDAOImp implements ProcAndMedDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProceduresAndMedicines() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProcedureAndMedicinePO").list();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProcedures(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcedureAndMedicinePO as p where p.type= :type");
        query.setParameter("type", TherapyType.PROCEDURE);
        return query.getResultList();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllMedicines(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcedureAndMedicinePO as p where p.type= :type");
        query.setParameter("type", TherapyType.MEDICINE);
        return query.getResultList();
    }

    @Override
    public void add(ProcedureAndMedicinePO procedureOrMedicine) {
        Session session = sessionFactory.getCurrentSession();
        session.persist("ProcedureAndMedicinePO", procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (ProcedureAndMedicinePO) session.get(ProcedureAndMedicinePO.class, id);
    }

    @Override
    public void delete(ProcedureAndMedicinePO procedureOrMedicine) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("ProcedureAndMedicinePO", procedureOrMedicine);
    }

    @Override
    public void update(ProcedureAndMedicinePO procedureOrMedicine) {
        Session session = sessionFactory.getCurrentSession();
        session.update("ProcedureAndMedicinePO", procedureOrMedicine);
    }
}
