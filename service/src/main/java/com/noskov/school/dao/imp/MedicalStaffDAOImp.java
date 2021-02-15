package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.persistent.MedicalStaffPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MedicalStaffDAOImp implements MedicalStaffDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(MedicalStaffPO staff) {
        entityManager.persist(staff);
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        return entityManager.find(MedicalStaffPO.class,id);
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        entityManager.remove(staff);
    }

    @Override
    public void update(MedicalStaffPO staff) {
        entityManager.merge(staff);
    }

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        return entityManager.createQuery("select s from MedicalStaffPO s", MedicalStaffPO.class).getResultList();
    }

    @Override
    public MedicalStaffPO getByEmail(String email) {
        Query query = entityManager.createQuery("select s from MedicalStaffPO as s " +
                "left join fetch s.post " +
                "left join fetch s.patients " +
                "where s.email = :email", MedicalStaffPO.class);
        query.setParameter("email", email);
        try {
            MedicalStaffPO medicalStaff = (MedicalStaffPO) query.getSingleResult();
            return medicalStaff;
        } catch (NoResultException e){
            return null;
        }
    }
}
