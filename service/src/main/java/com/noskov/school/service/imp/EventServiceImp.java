package com.noskov.school.service.imp;

import com.noskov.school.converters.EventServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.dto.ExportEventDTO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class EventServiceImp implements EventService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImp.class);

    private final EventServiceConverter converter;

    private final EventDAO eventDAO;

    private final PatientDAO patientDAO;

    private final PrescriptionDAO prescriptionDAO;

    @Autowired
    public EventServiceImp(EventServiceConverter converter, EventDAO eventDAO, PatientDAO patientDAO, PrescriptionDAO prescriptionDAO) {
        this.converter = converter;
        this.eventDAO = eventDAO;
        this.patientDAO = patientDAO;
        this.prescriptionDAO = prescriptionDAO;
    }

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
        LOGGER.info("Getting EventPO by id: {}", id);
        return eventDAO.getById(id);
    }

    @Override
    public void add(EventPO event) {
        eventDAO.add(event);
        LOGGER.info("Added event...");
    }

    @Override
    public void delete(Long id) {
        eventDAO.deleteById(id);
        LOGGER.info("Delete event");
    }

    @Override
    public void update(EventPO event) {
        eventDAO.update(event);
    }

    @Override
    public void deleteByPatientAndTherapy(PatientPO patientPO, ProcedureAndMedicinePO therapy) {
        eventDAO.deleteByPatientAndTherapy(patientPO,therapy);
        LOGGER.info("Delete by patient and therapy");
    }

    @Override
    public void changeStatus(Long id, EventStatus status) {
        eventDAO.changeStatus(id, status);
        LOGGER.info("Change status");
    }

    @Override
    public void setReasonToCancel(String reason, Long id) {
        eventDAO.setReasonToCancel(reason, id);
        LOGGER.info("Set reason to cancel");
    }

    @Override
    public String getDoseFromMedicineEvent(String dose, Long id) {
        return eventDAO.getDoseFromMedicineEvent(dose, id);
    }

    @Override
    public List<ExportEventDTO> getEventsForDayExternal() {
        LOGGER.info("Getting events for external use");
        List<EventPO> eventPOList = getEventsForDay();
        List<ExportEventDTO> eventDTOList = eventPOList.stream()
                .map(e -> converter.convertToDTO(e))
                .collect(Collectors.toList());
        return eventDTOList;
    }

    @Override
    public void cancelFromNowByPatientAndPrescription(Long patientId, Long prescriptionId) {
        LOGGER.info("Cancel from now for patient and prescription");
        PatientPO patient = patientDAO.getById(patientId);
        PrescriptionPO prescriptionPO =prescriptionDAO.getById(prescriptionId);
        ProcedureAndMedicinePO therapy = prescriptionPO.getPrescriptionType();
        //ProcedureAndMedicinePO therapy = prescriptionDAO.getById(prescriptionId).getPrescriptionType();
        eventDAO.deleteFromNowByPatientAndTherapy(patient, therapy);
    }
}
