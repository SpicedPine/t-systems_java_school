package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.enums.TherapyType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.Query;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class ProcAndMedDAOImp implements ProcAndMedDAO {
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    /*private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }*/

    @Override
    public List<ProcedureAndMedicinePO> getAllProceduresAndMedicines() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select p from ProcedureAndMedicinePO p").getResultList();

        /*Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from ProcedureAndMedicinePO").list();*/
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProcedures(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.type = :type");
        query.setParameter("type", TherapyType.PROCEDURE);
        return query.getResultList();

        /*Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcedureAndMedicinePO as p where p.type= :type");
        query.setParameter("type", TherapyType.PROCEDURE);
        return query.getResultList();*/
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllMedicines(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.type = :type");
        query.setParameter("type", TherapyType.MEDICINE);
        return query.getResultList();

        /*Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ProcedureAndMedicinePO as p where p.type= :type");
        query.setParameter("type", TherapyType.MEDICINE);
        return query.getResultList();*/
    }

    @Override
    public void add(ProcedureAndMedicinePO procedureOrMedicine) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(procedureOrMedicine);

        /*Session session = sessionFactory.getCurrentSession();
        session.persist("ProcedureAndMedicinePO", procedureOrMedicine);*/
    }

    @Override
    public ProcedureAndMedicinePO getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.id = :id");
        query.setParameter("id", id);
        return (ProcedureAndMedicinePO) query.getSingleResult();
    }

    @Override
    public void delete(ProcedureAndMedicinePO procedureOrMedicine) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(procedureOrMedicine);
    }

    @Override
    public void update(ProcedureAndMedicinePO procedureOrMedicine) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.refresh(procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getByName(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from ProcedureAndMedicinePO p where p.name = :name");
        query.setParameter("name", name);
        return (ProcedureAndMedicinePO) query.getSingleResult();
    }
}
