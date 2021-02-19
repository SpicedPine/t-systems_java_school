package com.noskov.school.dao.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EventDAOImp implements EventDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EventPO> getAllEvents() {
        List<EventPO> eventList = entityManager.createQuery("select distinct e from EventPO e "
                + "join fetch e.patient "
                + "join fetch e.eventType", EventPO.class).getResultList();
        return eventList;
    }

    @Override
    public List<EventPO> getEventsForDay() {
        Query query = entityManager.createQuery("select distinct e from EventPO as e " +
                "join fetch e.patient " +
                "join fetch e.eventType " +
                "where e.dateAndTime <: tomorrow " +
                "and e.dateAndTime >: yesterday", EventPO.class);
        query.setParameter("tomorrow", LocalDateTime.now().plusDays(1));
        query.setParameter("yesterday", LocalDateTime.now().minusDays(1));
        List<EventPO> poList = query.getResultList();
        return poList;
    }

    @Override
    public List<EventPO> getEventsFotHour() {
        Query query = entityManager.createQuery("select distinct e from EventPO as e " +
                "join fetch e.patient " +
                "join fetch e.eventType " +
                "where e.dateAndTime <: nextHour " +
                "and e.dateAndTime >: now", EventPO.class);
        query.setParameter("nextHour", LocalDateTime.now().plusHours(1));
        query.setParameter("now", LocalDateTime.now());
        List<EventPO> poList = query.getResultList();
        return poList;
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
    public void changeStatus(Long id, EventStatus status) {
        Query query = entityManager.createQuery("update EventPO as e set e.status =: status where e.id =: id");
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.executeUpdate();
    }

    @Override
    public void setReasonToCancel(String reason, Long id) {
        Query query = entityManager.createQuery("update EventPO as e set e.reasonToCancel =:reason where e.id =:id");
        query.setParameter("id", id);
        query.setParameter("reason", reason);
        query.executeUpdate();
    }

    @Override
    public String getDoseFromMedicineEvent(String dose, Long id) {
        Query query = entityManager.createQuery("select e from EventPO e where e.id = :id");
        query.setParameter("id", id);
        EventPO event = (EventPO) query.getSingleResult();
        return event.getDoseDescription();
    }
}
