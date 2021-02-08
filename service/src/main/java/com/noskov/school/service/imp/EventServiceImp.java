package com.noskov.school.service.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    EventDAO eventDAO;

    @Override
    public List<EventPO> getAll() {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<EventPO> getEventsForHour() {
        List<EventPO> eventList = eventDAO.getAllEvents();
        LocalTime nextHour = LocalTime.now().plusHours(1);
        LocalDate nextDate = LocalDate.now().plusDays(1);//why plus day here ?
        LocalTime now = LocalTime.now();
        eventList = eventList.stream() //todo ай ай ай фильтрация в Java !!!
                .filter(e -> e.getDateAndTime().toLocalTime().isBefore(nextHour))
                .filter(e -> e.getDateAndTime().toLocalDate().isBefore(nextDate))
                .filter(e -> e.getDateAndTime().toLocalTime().isAfter(now))
                .collect(Collectors.toList());
        return eventList;
    }

    @Override
    public List<EventPO> getEventsForDay() {
        List<EventPO> eventList = eventDAO.getAllEvents();
        LocalDate nextDay = LocalDate.now().plusDays(1); //todo ай ай ай фильтрация в Java !!!
        eventList = eventList.stream()
                .filter(e -> e.getDateAndTime().toLocalDate().isBefore(nextDay))
                .collect(Collectors.toList());
        return eventList;
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
    public void changeStatusToDone(Long id){
        eventDAO.changeStatusToDone(id);
    }

    @Override
    public void changeStatusToCancelled(long id) {
        eventDAO.changeStatusToCancelled(id);
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
