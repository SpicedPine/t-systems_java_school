package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
