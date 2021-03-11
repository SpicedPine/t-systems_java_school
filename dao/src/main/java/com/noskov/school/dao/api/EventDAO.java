package com.noskov.school.dao.api;

import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface EventDAO {
    List<EventPO> getAllEvents();
    List<EventPO> getEventsForDay();
    List<EventPO> getEventsFotHour();
    void add(EventPO eventPO);
    EventPO getById(Long id);
    void delete(EventPO event);
    void deleteById(Long id);
    void update(EventPO event);

    void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy);

    void changeStatus(Long id, EventStatus status);

    void setReasonToCancel(String reason ,Long id);

    String getDoseFromMedicineEvent(String dose, Long id);

    void deleteEventsFromNowForPatient(PatientPO patientPO);

    void deleteFromNowByPatientAndTherapy(PatientPO patient, ProcedureAndMedicinePO therapy);
}
