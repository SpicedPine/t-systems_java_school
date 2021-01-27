package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EventDAOImp implements EventDAO {
    private SessionFactory sessionFactory;

    @Autowired
    void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<EventPO> getAllEvents() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from EventPO").list();
    }

    @Override
    public void add(EventPO eventPO) {
        Session session = sessionFactory.getCurrentSession();
        session.persist("EventPO", eventPO);
    }

    @Override
    public EventPO getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(EventPO.class, id);
     }

    @Override
    public void delete(EventPO event) {
        Session session = sessionFactory.getCurrentSession();
        session.delete("EventPO", event);
    }

    @Override
    public void update(EventPO event) {
        Session session = sessionFactory.getCurrentSession();
        session.update("EventPO", event);
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from EventPO as e where e.id = :id").setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from EventPO as e where e.patient =:patientPO and e.eventType =:therapy");
        query.setParameter("patientPO", patientPO);
        query.setParameter("therapy", therapy);
        query.executeUpdate();
    }
}
