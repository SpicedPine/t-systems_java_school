package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.persistent.MedicalStaffPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MedicalStaffDAOImp implements MedicalStaffDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalStaffDAOImp.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(MedicalStaffPO staff) {
        entityManager.persist(staff);

        LOGGER.debug("staff with email {} was persisted", staff.getEmail());
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        return entityManager.find(MedicalStaffPO.class,id);
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        entityManager.remove(staff);

        LOGGER.debug("staff with email {} was removed", staff.getEmail());
    }

    @Override
    public void update(MedicalStaffPO staff) {
        entityManager.merge(staff);

        LOGGER.debug("staff with email {} was updated", staff.getEmail());
    }

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        LOGGER.debug("returned all staff");

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
            LOGGER.debug("returned medical staff with email {}", email);
            return medicalStaff;
        } catch (NoResultException e){
            LOGGER.info("returned null instead of staffPO");
            return null;
        }
    }
}
