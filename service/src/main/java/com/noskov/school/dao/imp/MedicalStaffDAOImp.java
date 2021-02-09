package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.enums.StaffPost;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.MedicalStaffPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
//import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MedicalStaffDAOImp implements MedicalStaffDAO {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void add(MedicalStaffPO staff) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(staff);
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select s from MedicalStaffPO s where s.id = :id");
        query.setParameter("id", id);
        return (MedicalStaffPO) query.getSingleResult();
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(staff);
    }

    @Override
    public void update(MedicalStaffPO staff) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.refresh(staff);
    }

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select s from MedicalStaffPO s").getResultList();
    }
}
