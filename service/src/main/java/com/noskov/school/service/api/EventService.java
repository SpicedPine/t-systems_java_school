package com.noskov.school.service.api;

import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface EventService {
    List<EventPO> getAll();

    List<EventPO> getEventsForDay(); //todo think of passing filter params instead of 3 different methods

    List<EventPO> getEventsForHour();

    EventPO getOne(Long id);

    void add(EventPO event);

    void delete(Long id);

    void update(EventPO event);

    void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy);

    //todo consider to pass DONE / CANCEL as a status (to reduce methos quantity)
    void changeStatusToDone(Long id);

    void changeStatusToCancelled(long id); //why different long ?

    void setReasonToCancel(String reason ,Long id);

    String getDoseFromMedicineEvent(String dose, Long id);
}
