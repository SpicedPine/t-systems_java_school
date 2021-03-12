package com.noskov.school.service.imp;

import com.noskov.school.converters.EventServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.enums.EventStatus;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImpTest {

    private EventServiceImp service;
    private EventDAO eventDAO;
    private PatientDAO patientDAO;
    private PrescriptionDAO prescriptionDAO;
    private EventServiceConverter converter;

    @BeforeEach
    private void init(){
        eventDAO = mock(EventDAO.class);
        patientDAO = mock(PatientDAO.class);
        prescriptionDAO = mock(PrescriptionDAO.class);
        converter = mock(EventServiceConverter.class);

        service = new EventServiceImp(converter, eventDAO, patientDAO, prescriptionDAO);
    }

    @Test
    void getAll() {
        List<EventPO> list = List.of(new EventPO());
        when(eventDAO.getAllEvents()).thenReturn(list);
        assertEquals(service.getAll(), list);
    }

    @Test
    void getEventsForHour() {
        List<EventPO> list = List.of(new EventPO());
        when(eventDAO.getEventsFotHour()).thenReturn(list);
        assertEquals(service.getEventsForHour(), list);
    }

    @Test
    void getEventsForDay() {
        List<EventPO> list = List.of(new EventPO());
        when(eventDAO.getEventsForDay()).thenReturn(list);
        assertEquals(service.getEventsForDay(), list);
    }

    @Test
    void getOne() {
        Long id = 1L;
        EventPO eventPO = mock(EventPO.class);
        when(eventDAO.getById(id)).thenReturn(eventPO);
        assertEquals(service.getOne(id), eventPO);
    }

    @Test
    void add() {
        EventPO eventPO = mock(EventPO.class);
        doNothing().when(eventDAO).add(eventPO);
        service.add(eventPO);
        verify(eventDAO, times(1)).add(eventPO);
        verifyNoMoreInteractions(eventDAO, eventPO, prescriptionDAO, patientDAO);
    }

    @Test
    void delete() {
        Long id = 1L;
        doNothing().when(eventDAO).deleteById(id);
        service.delete(id);
        verify(eventDAO, times(1)).deleteById(id);
        verifyNoMoreInteractions(eventDAO, prescriptionDAO, patientDAO);
    }

    @Test
    void update() {
        EventPO eventPO = mock(EventPO.class);
        doNothing().when(eventDAO).update(eventPO);
        service.update(eventPO);
        verify(eventDAO, times(1)).update(eventPO);
        verifyNoMoreInteractions(eventDAO, eventPO, prescriptionDAO, patientDAO);
    }

    @Test
    void deleteByPatientAndTherapy() {
        PatientPO patient = mock(PatientPO.class);
        ProcedureAndMedicinePO therapy = mock(ProcedureAndMedicinePO.class);
        doNothing().when(eventDAO).deleteByPatientAndTherapy(patient,therapy);
        service.deleteByPatientAndTherapy(patient,therapy);
        verify(eventDAO, times(1)).deleteByPatientAndTherapy(patient,therapy);
        verifyNoMoreInteractions(eventDAO, prescriptionDAO, patientDAO ,patient, therapy);
    }

    @Test
    void changeStatus() {
        Long id = 1L;
        EventStatus status = EventStatus.CANCELED;
        doNothing().when(eventDAO).changeStatus(id, status);
        service.changeStatus(id, status);
        verify(eventDAO, times(1)).changeStatus(id, status);
        verifyNoMoreInteractions(eventDAO, prescriptionDAO, patientDAO);
    }

    @Test
    void setReasonToCancel() {
        Long id = 1L;
        String reasonToCancel = "";
        doNothing().when(eventDAO).setReasonToCancel(reasonToCancel,id);
        service.setReasonToCancel(reasonToCancel,id);
        verify(eventDAO, times(1)).setReasonToCancel(reasonToCancel,id);
        verifyNoMoreInteractions(eventDAO, prescriptionDAO, patientDAO);
    }

    @Test
    void getDoseFromMedicineEvent() {

    }

    @Test
    void getEventsForDayExternal() {
        List<EventPO> list = List.of(new EventPO(),new EventPO(),new EventPO(),new EventPO());
        when(eventDAO.getEventsForDay()).thenReturn(list);
        when(converter.convertToDTO(any())).thenReturn(null);
        service.getEventsForDayExternal();
        verify(eventDAO,times(1)).getEventsForDay();
        verify(converter,times(list.size())).convertToDTO(any());
        verifyNoMoreInteractions(eventDAO, converter, prescriptionDAO, patientDAO);
    }

    @Test
    void cancelFromNowByPatientAndPrescription() {
        Long patientId = 1L;
        Long prescriptionId = 2L;
        PatientPO patient = mock(PatientPO.class);
        ProcedureAndMedicinePO therapy = mock(ProcedureAndMedicinePO.class);
        PrescriptionPO prescription = mock(PrescriptionPO.class);
        when(prescriptionDAO.getById(prescriptionId)).thenReturn(prescription);
        when(patientDAO.getById(patientId)).thenReturn(patient);
        when(prescription.getPrescriptionType()).thenReturn(therapy);
        doNothing().when(eventDAO).deleteFromNowByPatientAndTherapy(patient, therapy);
        service.cancelFromNowByPatientAndPrescription(patientId, prescriptionId);
        verify(patientDAO, times(1)).getById(patientId);
        verify(prescriptionDAO, times(1)).getById(prescriptionId);
        verify(prescription, times(1)).getPrescriptionType();
        verify(eventDAO,times(1)).deleteFromNowByPatientAndTherapy(patient, therapy);
        verifyNoMoreInteractions(eventDAO, converter, patientDAO, prescriptionDAO, patient, therapy);
    }
}