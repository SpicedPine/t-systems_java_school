package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.enums.StaffPost;
import com.noskov.school.persistent.MedicalStaffPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MedicalStaffDAOImp implements MedicalStaffDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(MedicalStaffPO staff) {
        Session session = sessionFactory.getCurrentSession();
        session.persist("MedicalStaffPO", staff);
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(MedicalStaffPO.class, id);
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("MedicalStaffPO", staff);
    }

    @Override
    public void update(MedicalStaffPO staff) {
        Session session = sessionFactory.getCurrentSession();
        session.update("MedicalStaffPO", staff);
    }

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from MedicalStaffPO").list();
    }
}
