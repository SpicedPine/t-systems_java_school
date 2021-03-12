package com.noskov.school.service.api;

import com.noskov.school.dto.ExportEventDTO;
import com.noskov.school.dto.InnerEventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface EventService {
    List<InnerEventDTO> getAll();

    List<InnerEventDTO> getEventsForDay();

    List<ExportEventDTO> getEventsForDayExternal();

    List<InnerEventDTO> getEventsForHour();

    InnerEventDTO getOne(Long id);

    //void add(InnerEventDTO event);

    void delete(Long id);

    //void update(InnerEventDTO event);

    void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy);

    void changeStatus(Long id, EventStatus status);

    void setReasonToCancel(String reason ,Long id);

    String getDoseFromMedicineEvent(String dose, Long id);

    void cancelFromNowByPatientAndPrescription(Long patientId, Long prescriptionId);
}
