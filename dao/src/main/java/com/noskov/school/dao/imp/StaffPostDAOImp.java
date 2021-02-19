package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.StaffPostDAO;
import com.noskov.school.persistent.StaffPostPO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StaffPostDAOImp implements StaffPostDAO {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<StaffPostPO> getAllPosts() {
        List<StaffPostPO> list = entityManager.createQuery("select p from StaffPostPO as p", StaffPostPO.class).getResultList();
        return list;
    }

    @Override
    public StaffPostPO getById(Long id) {
        Query query = entityManager.createQuery("select p from StaffPostPO as p where p.id = :id");
        query.setParameter("id", id);
        return (StaffPostPO) query.getSingleResult();
    }
}
