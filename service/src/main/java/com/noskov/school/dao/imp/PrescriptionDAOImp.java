package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PrescriptionPO;
import javax.persistence.Query;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class PrescriptionDAOImp implements PrescriptionDAO {
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
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from PrescriptionPO p where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        /*Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from PrescriptionPO as p where p.id = :id").setParameter("id", id);
        query.executeUpdate();*/
    }

    @Override
    public List<PrescriptionPO> getAllPrescriptions() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select p from PrescriptionPO p").getResultList();

        /*Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from PrescriptionPO").list();*/
    }

    @Override
    public void add(PrescriptionPO prescription) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(prescription);

        /*Session session = sessionFactory.getCurrentSession();
        session.persist("PrescriptionPO", prescription);*/
    }

    @Override
    public PrescriptionPO getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select p from PrescriptionPO p where p.id = :id");
        query.setParameter("id", id);
        PrescriptionPO prescription = (PrescriptionPO) query.getSingleResult();
        if(prescription != null){
            return prescription;
        } else{
            throw new NullPointerException("from getById in PrescriptionServiceImp");
        }
    }

    @Override
    public void delete(PrescriptionPO prescription) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(prescription);
    }

    @Override
    public void update(PrescriptionPO prescription) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.refresh(prescription);
    }
}
