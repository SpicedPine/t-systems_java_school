package com.noskov.school.service.imp;

import com.noskov.school.converters.PatientServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.enums.PatientStatus;
import com.noskov.school.persistent.PatientPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PatientServiceImpTest {

    private PatientServiceImp patientService;

    private EventDAO eventDAO;
    private PatientDAO patientDAO;
    private PrescriptionDAO prescriptionDAO;
    private PatientServiceConverter converter;

    @BeforeEach
    private void init(){
        eventDAO = mock(EventDAO.class);
        patientDAO = mock(PatientDAO.class);
        prescriptionDAO = mock(PrescriptionDAO.class);
        converter = mock(PatientServiceConverter.class);

        patientService = new PatientServiceImp(eventDAO, patientDAO,
                prescriptionDAO, converter);
    }

    @Test
    void getAll() {
        when(patientDAO.getAllPatients()).thenReturn(null);
        patientService.getAll();
        verify(patientDAO, times(1)).getAllPatients();
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void getOne() {
        Long id = 1L;
        when(patientDAO.getById(id)).thenReturn(null);
        patientService.getOne(id);
        verify(patientDAO, times(1)).getById(id);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void add() {
        PatientPO patientPO = mock(PatientPO.class);
        doNothing().when(patientDAO).add(patientPO);
        patientService.add(patientPO);
        verify(patientDAO, times(1)).add(patientPO);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void delete() {
        Long id = 1L;
        doNothing().when(patientDAO).deleteById(id);
        patientService.delete(id);
        verify(patientDAO, times(1)).deleteById(id);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void update() {
        PatientPO patientPO = mock(PatientPO.class);
        doNothing().when(patientDAO).update(patientPO);
        patientService.update(patientPO);
        verify(patientDAO, times(1)).update(patientPO);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void getPatientProfile() {
        Long id = 1L;
        PatientPO patientPO = mock(PatientPO.class);
        when(patientDAO.getById(id)).thenReturn(patientPO);
        when(prescriptionDAO.getPrescriptionsByPatient(patientPO)).thenReturn(null);
        when(converter.patientProfile(patientPO,null)).thenReturn(null);
        patientService.getPatientProfile(id);
        verify(prescriptionDAO, times(1)).getPrescriptionsByPatient(patientPO);
        verify(patientDAO,times(1)).getById(id);
        verify(converter, times(1)).patientProfile(patientPO, null);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void addIfNotExist() {
        PatientPO patientPO = mock(PatientPO.class);
        when(patientPO.getSocialNumber()).thenReturn(1);
        when(patientDAO.getBySocialNumber(1)).thenReturn(patientPO);
        patientService.addIfNotExist(patientPO);
        verify(patientPO,times(1)).getSocialNumber();
        verify(patientDAO, times(1)).getBySocialNumber(1);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void addIfNotExist2() {
        PatientPO patientPO = mock(PatientPO.class);
        when(patientPO.getSocialNumber()).thenReturn(1);
        when(patientDAO.getBySocialNumber(1)).thenReturn(null);
        patientService.addIfNotExist(patientPO);
        verify(patientPO,times(1)).getSocialNumber();
        verify(patientDAO, times(1)).getBySocialNumber(1);
        verify(patientDAO,times(1)).add(patientPO);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void checkExistenceBySocialNumber() {
        int socialNumber = 1;
        when(patientDAO.getBySocialNumber(1)).thenReturn(null);
        patientService.checkExistenceBySocialNumber(socialNumber);
        verify(patientDAO, times(1)).getBySocialNumber(socialNumber);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void getBySocialNumber() {
        int socialNumber = 1;
        PatientPO patientPO = mock(PatientPO.class);
        when(patientDAO.getBySocialNumber(socialNumber)).thenReturn(patientPO);
        patientService.getBySocialNumber(socialNumber);
        verify(patientDAO, times(1)).getBySocialNumber(socialNumber);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }

    @Test
    void getBySocialNumberWithNullResponse() {
        int socialNumber = 1;
        PatientPO patientPO = mock(PatientPO.class);
        when(patientDAO.getBySocialNumber(socialNumber)).thenReturn(null);
        Assertions.assertThrows(RuntimeException.class, () -> patientService.getBySocialNumber(socialNumber));
    }

    @Test
    void release() {
        Long id = 1L;
        PatientPO patientPO = mock(PatientPO.class);
        doNothing().when(patientPO).setStatus(PatientStatus.TREATED);
        when(patientDAO.getById(id)).thenReturn(patientPO);
        doNothing().when(eventDAO).deleteEventsFromNowForPatient(patientPO);
        doNothing().when(prescriptionDAO).deletePrescriptionsByPatient(patientPO);
        patientService.release(id);
        verify(patientDAO,times(1)).getById(id);
        verify(patientPO,times(1)).setStatus(PatientStatus.TREATED);
        verify(eventDAO, times(1)).deleteEventsFromNowForPatient(patientPO);
        verify(prescriptionDAO,times(1)).deletePrescriptionsByPatient(patientPO);
        verifyNoMoreInteractions(eventDAO, patientDAO, prescriptionDAO, converter);
    }
}