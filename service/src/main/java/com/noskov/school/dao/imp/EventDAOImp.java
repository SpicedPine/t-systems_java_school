package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class EventDAOImp implements EventDAO {
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<EventPO> getAllEvents() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<EventPO> eventList = entityManager.createQuery("select distinct e from EventPO e "
                + "join fetch e.patient "
                + "join fetch e.eventType").getResultList();
        return eventList;
    }

    @Override
    public void add(EventPO eventPO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(eventPO);
    }

    @Override
    public EventPO getById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //entityManager.find(EventPO.class,id);
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        return (EventPO) query.getSingleResult();
     }

    @Override
    public void delete(EventPO event) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.remove(event);
    }

    @Override
    public void update(EventPO event) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.merge(event);
    }

    @Override
    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from EventPO e where e.id = :id").setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("delete from EventPO e where e.patient = :patientPO and e.eventType =:therapy");
        query.setParameter("patientPO", patientPO);
        query.setParameter("therapy", therapy);
        query.executeUpdate();
    }

    @Override
    public void changeStatusToDone(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from EventPO e where e.id =:id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setStatus(EventStatus.DONE);
        update(event);
    }

    @Override
    public void changeStatusToCancelled(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setStatus(EventStatus.CANCELED);
        update(event);
    }

    @Override
    public void setReasonToCancel(String reason, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setReasonToCancel(reason);
    }

    @Override
    public String getDoseFromMedicineEvent(String dose, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        return event.getDoseDescription();
    }
}
