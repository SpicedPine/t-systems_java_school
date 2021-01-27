package com.noskov.school.dao.api;

import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface EventDAO {
    List<EventPO> getAllEvents();
    void add(EventPO eventPO);
    EventPO getById(Long id);
    void delete(EventPO event);
    void deleteById(Long id);
    void update(EventPO event);

    void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy);
}
