package com.noskov.school.service.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EventServiceImp implements EventService {
    @Autowired
    private EventDAO eventDAO;

    @Override
    public List<EventPO> getAll() {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<EventPO> getEventsForHour() {
        return eventDAO.getEventsFotHour();
    }

    @Override
    public List<EventPO> getEventsForDay() {
        return eventDAO.getEventsForDay();
    }

    @Override
    public EventPO getOne(Long id) {
        return eventDAO.getById(id);
    }

    @Override
    public void add(EventPO event) {
        eventDAO.add(event);
    }

    @Override
    public void delete(Long id) {
        eventDAO.deleteById(id);
    }

    @Override
    public void update(EventPO event) {
        eventDAO.update(event);
    }

    @Override
    public void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy) {
        eventDAO.deleteByPatientAndTherapy(patientPO,therapy);
    }

    @Override
    public void changeStatus(Long id, EventStatus status) {
        eventDAO.changeStatus(id, status);
    }

    @Override
    public void setReasonToCancel(String reason, Long id) {
        eventDAO.setReasonToCancel(reason, id);
    }

    @Override
    public String getDoseFromMedicineEvent(String dose, Long id) {
        return eventDAO.getDoseFromMedicineEvent(dose, id);
    }
}
