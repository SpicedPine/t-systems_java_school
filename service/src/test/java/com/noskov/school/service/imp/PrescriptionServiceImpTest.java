package com.noskov.school.service.imp;

import com.noskov.school.converters.PrescriptionServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventGenerationService;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrescriptionServiceImpTest {
    private PrescriptionServiceImp service;

    private PrescriptionDAO prescriptionDAO;
    private PatientDAO patientDAO;
    private ProcAndMedDAO procAndMedDAO;
    private EventDAO eventDAO;
    private EventGenerationServiceImp eventGenerationService;
    private PrescriptionServiceConverter converter;

    @BeforeEach
    private void init(){
        prescriptionDAO = mock(PrescriptionDAO.class);
        patientDAO = mock(PatientDAO.class);
        procAndMedDAO = mock(ProcAndMedDAO.class);
        eventDAO = mock(EventDAO.class);
        eventGenerationService = mock(EventGenerationServiceImp.class);
        converter = mock(PrescriptionServiceConverter.class);

        service = new PrescriptionServiceImp(prescriptionDAO, patientDAO,
                procAndMedDAO, eventDAO, eventGenerationService, converter);
    }

    @Test
    void getAll() {
        List<PrescriptionPO> list = List.of(new PrescriptionPO(),new PrescriptionPO(),new PrescriptionPO());
        when(prescriptionDAO.getAllPrescriptions()).thenReturn(list);
        when(converter.convertToDTO(any())).thenReturn(null);
        service.getAll();
        verify(prescriptionDAO, times(1)).getAllPrescriptions();
        verify(converter, times(list.size())).convertToDTO(any());
        verifyNoMoreInteractions(prescriptionDAO, patientDAO,
                procAndMedDAO, eventDAO, eventGenerationService, converter);
    }

    @Test
    void getOne() {

    }

    @Test
    void add() throws Exception {
        Long patientId = 1L;
        PrescriptionDTO prescriptionDTO = mock(PrescriptionDTO.class);
        PatientPO patient = mock(PatientPO.class);
        PrescriptionPO prescriptionPO = mock(PrescriptionPO.class);
        PrescriptionScratch scratch = mock(PrescriptionScratch.class);
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);

        doNothing().when(prescriptionDTO).setPatient(patient);
        doNothing().when(prescriptionDTO).setProcOrMedicine(procedureAndMedicinePO);
        when(patientDAO.getById(patientId)).thenReturn(patient);
        when(prescriptionDTO.getScratch()).thenReturn(scratch);
        when(scratch.getTypeTherapyName()).thenReturn("");
        when(procAndMedDAO.getByName("")).thenReturn(procedureAndMedicinePO);
        when(converter.convertToPO(prescriptionDTO)).thenReturn(prescriptionPO);
        doNothing().when(eventGenerationService).generateEvents(prescriptionPO);
        doNothing().when(prescriptionDAO).add(prescriptionPO);

        service.add(prescriptionDTO, patientId);
        verify(prescriptionDTO,times(1)).setPatient(patient);
        verify(prescriptionDTO,times(1)).setProcOrMedicine(procedureAndMedicinePO);
        verify(patientDAO,times(1)).getById(patientId);
        verify(prescriptionDTO,times(1)).getScratch();
        verify(scratch,times(1)).getTypeTherapyName();
        verify(procAndMedDAO,times(1)).getByName("");
        verify(converter,times(1)).convertToPO(prescriptionDTO);
        verify(eventGenerationService, times(1)).generateEvents(prescriptionPO);
        verify(prescriptionDAO,times(1)).add(prescriptionPO);
        verifyNoMoreInteractions(prescriptionDAO, patientDAO,
                procAndMedDAO, eventDAO, eventGenerationService, converter);
    }

    @Test
    void delete() {
        Long id = 1L;
        PatientPO patient = mock(PatientPO.class);
        PrescriptionPO prescriptionPO = mock(PrescriptionPO.class);
        ProcedureAndMedicinePO therapy = mock(ProcedureAndMedicinePO.class);

        when(prescriptionDAO.getById(id)).thenReturn(prescriptionPO);
        when(prescriptionPO.getPatient()).thenReturn(patient);
        when(prescriptionPO.getPrescriptionType()).thenReturn(therapy);
        doNothing().when(eventDAO).deleteByPatientAndTherapy(patient, therapy);
        doNothing().when(prescriptionDAO).deleteById(id);

        service.delete(id);
        verify(prescriptionDAO,times(1)).getById(id);
        verify(prescriptionPO,times(1)).getPatient();
        verify(prescriptionPO,times(1)).getPrescriptionType();
        verify(eventDAO,times(1)).deleteByPatientAndTherapy(patient, therapy);
        verify(prescriptionDAO,times(1)).deleteById(id);
        verifyNoMoreInteractions(prescriptionDAO, patientDAO,
                procAndMedDAO, eventDAO, eventGenerationService, converter);

    }

    @Test
    void update() {
    }

    @Test
    void getPrescriptionsByPatient() {
    }

    @Test
    void editPrescription() throws Exception {
        Long patientId = 1L;
        Long prescriptionId = 2L;
        PrescriptionPO prescriptionPO1 = mock(PrescriptionPO.class);
        PrescriptionPO prescriptionPO2 = mock(PrescriptionPO.class);
        PrescriptionDTO prescriptionDTO1 = mock(PrescriptionDTO.class);
        PrescriptionDTO prescriptionDTO2 = mock(PrescriptionDTO.class);
        PatientPO patient = mock(PatientPO.class);
        PrescriptionScratch scratch = mock(PrescriptionScratch.class);
        ProcedureAndMedicinePO oldProcOrMed = mock(ProcedureAndMedicinePO.class);
        ProcedureAndMedicinePO newProcOrMed = mock(ProcedureAndMedicinePO.class);

        when(patientDAO.getById(patientId)).thenReturn(patient);
        when(prescriptionDTO1.getScratch()).thenReturn(scratch);
        when(scratch.getTypeTherapyName()).thenReturn("");
        when(prescriptionDAO.getById(prescriptionId)).thenReturn(prescriptionPO1);
        when(converter.convertToDTO(prescriptionPO1)).thenReturn(prescriptionDTO2);
        when(prescriptionDTO2.getProcOrMedicine()).thenReturn(oldProcOrMed);
        doNothing().when(eventDAO).deleteByPatientAndTherapy(patient, oldProcOrMed);
        doNothing().when(prescriptionDTO1).setPatient(patient);
        doNothing().when(prescriptionDTO1).setProcOrMedicine(newProcOrMed);
        when(procAndMedDAO.getByName("")).thenReturn(newProcOrMed);
        when(converter.convertToPO(prescriptionDTO1)).thenReturn(prescriptionPO2);
        doNothing().when(eventGenerationService).generateEvents(prescriptionPO2);

        service.editPrescription(patientId, prescriptionId, prescriptionDTO1);
        verify(patientDAO,times(1)).getById(patientId);
        verify(prescriptionDTO1,times(1)).getScratch();
        verify(scratch,times(1)).getTypeTherapyName();
        verify(prescriptionDAO,times(1)).getById(prescriptionId);
        verify(converter,times(1)).convertToDTO(prescriptionPO1);
        verify(prescriptionDTO2,times(1)).getProcOrMedicine();
        verify(eventDAO,times(1)).deleteByPatientAndTherapy(patient, oldProcOrMed);
        verify(prescriptionDTO1,times(1)).setPatient(patient);
        verify(procAndMedDAO,times(1)).getByName("");
        verify(prescriptionDTO1,times(1)).setProcOrMedicine(newProcOrMed);
        verify(converter,times(2)).convertToPO(prescriptionDTO1);
        verify(eventGenerationService,times(1)).generateEvents(prescriptionPO2);

    }
}