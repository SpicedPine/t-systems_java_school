package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.persistent.EventPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
        return (EventPO) session.get(EventPO.class, id);
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
}