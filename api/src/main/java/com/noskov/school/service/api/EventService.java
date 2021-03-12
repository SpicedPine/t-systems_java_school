package com.noskov.school.service.api;

import com.noskov.school.dto.ExportEventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EventService {
    List<EventPO> getAll();

    List<EventPO> getEventsForDay();

    List<ExportEventDTO> getEventsForDayExternal();

    List<EventPO> getEventsForHour();

    EventPO getOne(Long id);

    void add(EventPO event);

    void delete(Long id);

    void update(EventPO event);

    void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy);

    void changeStatus(Long id, EventStatus status);

    void setReasonToCancel(String reason ,Long id);

    String getDoseFromMedicineEvent(String dose, Long id);

    void cancelFromNowByPatientAndPrescription(Long patientId, Long prescriptionId);
}
