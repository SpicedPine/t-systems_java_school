package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EventDAOImp implements EventDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventPO> getAllEvents() {
        List<EventPO> eventList = entityManager.createQuery("select distinct e from EventPO e "
                + "join fetch e.patient "
                + "join fetch e.eventType").getResultList();
        return eventList;
    }

    @Override
    public void add(EventPO eventPO) {
        entityManager.persist(eventPO);
    }

    @Override
    public EventPO getById(Long id) {
        return entityManager.find(EventPO.class,id);
     }

    @Override
    public void delete(EventPO event) {
        entityManager.remove(event);
    }

    @Override
    public void update(EventPO event) {
        entityManager.merge(event);
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from EventPO e where e.id = :id").setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy) {
        Query query = entityManager.createQuery("delete from EventPO e where e.patient = :patientPO and e.eventType =:therapy");
        query.setParameter("patientPO", patientPO);
        query.setParameter("therapy", therapy);
        query.executeUpdate();
    }

    @Override
    public void changeStatusToDone(Long id) {
        Query query = entityManager.createQuery("select e from EventPO e where e.id =:id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setStatus(EventStatus.DONE);
        update(event);
    }

    @Override
    public void changeStatusToCancelled(Long id) {
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setStatus(EventStatus.CANCELED);
        update(event);
    }

    @Override
    public void setReasonToCancel(String reason, Long id) {
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        event.setReasonToCancel(reason);
    }

    @Override
    public String getDoseFromMedicineEvent(String dose, Long id) {
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        return event.getDoseDescription();
    }
}
