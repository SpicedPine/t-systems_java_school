package com.noskov.school.service.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.enums.TherapyType;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventGenerationServiceImpTest {
    private static final String PROCEDURE_FORMED_PRESCRIPTION_1 = "procedure mrt 1 week on friday 2 week";
    private static final String PROCEDURE_FORMED_PRESCRIPTION_2 = "procedure kt 1 week on thursday 2 month";
    private static final String PROCEDURE_FORMED_PRESCRIPTION_3 = "procedure uzi 1 month on thursday 6 month";
    private static final String MEDICINE_FORMED_PRESCRIPTION_1 = "medicine aspirin 2 day at morning and evening before meal 4 day 1/2";
    private static final String MEDICINE_FORMED_PRESCRIPTION_2 = "medicine mezim 2 day 1 week 1/3";


    private EventGenerationServiceImp eventGenerationService;
    private EventDAO eventDAO;

    @BeforeEach
    private void init(){
        eventDAO = mock(EventDAO.class);
        eventGenerationService = new EventGenerationServiceImp(eventDAO);
    }

    @Test
    @DisplayName("Events for 1st procedure prescription")
    void generateProcedureEvents1() {
        PatientPO patientPO = new PatientPO();
        ProcedureAndMedicinePO procedure = new ProcedureAndMedicinePO();
        procedure.setName("mrt");
        procedure.setType(TherapyType.PROCEDURE);
        procedure.setEventList(new ArrayList<>());
        PrescriptionPO prescriptionPO = new PrescriptionPO();
        procedure.setPrescriptionList(List.of(prescriptionPO));
        prescriptionPO.setId(1L);
        prescriptionPO.setPrescriptionType(procedure);
        prescriptionPO.setPatient(patientPO);
        prescriptionPO.setFormedPrescription(PROCEDURE_FORMED_PRESCRIPTION_1);
        doNothing().when(eventDAO).add(any());
        eventGenerationService.generateEvents(prescriptionPO);
        int countOfGeneratedEvents = 2;
        verify(eventDAO,times(countOfGeneratedEvents)).add((any()));
    }

    @Test
    @DisplayName("Events for 2nd procedure prescription")
    void generateProcedureEvents2() {
        PatientPO patientPO = new PatientPO();
        ProcedureAndMedicinePO procedure = new ProcedureAndMedicinePO();
        procedure.setName("kt");
        procedure.setType(TherapyType.PROCEDURE);
        procedure.setEventList(new ArrayList<>());
        PrescriptionPO prescriptionPO = new PrescriptionPO();
        procedure.setPrescriptionList(List.of(prescriptionPO));
        prescriptionPO.setId(1L);
        prescriptionPO.setPrescriptionType(procedure);
        prescriptionPO.setPatient(patientPO);
        prescriptionPO.setFormedPrescription(PROCEDURE_FORMED_PRESCRIPTION_2);
        doNothing().when(eventDAO).add(any());
        eventGenerationService.generateEvents(prescriptionPO);
        int countOfGeneratedEvents = 8;
        verify(eventDAO,times(countOfGeneratedEvents)).add((any()));
    }

    @Test
    @DisplayName("Events for 3d procedure prescription")
    void generateProcedureEvents3() {
        PatientPO patientPO = new PatientPO();
        ProcedureAndMedicinePO procedure = new ProcedureAndMedicinePO();
        procedure.setName("uzi");
        procedure.setType(TherapyType.PROCEDURE);
        procedure.setEventList(new ArrayList<>());
        PrescriptionPO prescriptionPO = new PrescriptionPO();
        procedure.setPrescriptionList(List.of(prescriptionPO));
        prescriptionPO.setId(1L);
        prescriptionPO.setPrescriptionType(procedure);
        prescriptionPO.setPatient(patientPO);
        prescriptionPO.setFormedPrescription(PROCEDURE_FORMED_PRESCRIPTION_3);
        doNothing().when(eventDAO).add(any());
        eventGenerationService.generateEvents(prescriptionPO);
        int countOfGeneratedEvents = 6;
        verify(eventDAO,times(countOfGeneratedEvents)).add((any()));
    }

    @Test
    @DisplayName("Events for 1st medicine prescription")
    void generateMedicineEvents1() {
        PatientPO patientPO = new PatientPO();
        ProcedureAndMedicinePO medicine = new ProcedureAndMedicinePO();
        medicine.setName("aspirin");
        medicine.setType(TherapyType.MEDICINE);
        medicine.setEventList(new ArrayList<>());
        PrescriptionPO prescriptionPO = new PrescriptionPO();
        medicine.setPrescriptionList(List.of(prescriptionPO));
        prescriptionPO.setId(1L);
        prescriptionPO.setPrescriptionType(medicine);
        prescriptionPO.setPatient(patientPO);
        prescriptionPO.setFormedPrescription(MEDICINE_FORMED_PRESCRIPTION_1);
        doNothing().when(eventDAO).add(any());
        eventGenerationService.generateEvents(prescriptionPO);
        int countOfGeneratedEvents = 8;
        verify(eventDAO,times(countOfGeneratedEvents)).add((any()));
    }

    @Test
    @DisplayName("Events for 2st medicine prescription")
    void generateMedicineEvents2() {
        PatientPO patientPO = new PatientPO();
        ProcedureAndMedicinePO medicine = new ProcedureAndMedicinePO();
        medicine.setName("mezim");
        medicine.setType(TherapyType.MEDICINE);
        medicine.setEventList(new ArrayList<>());
        PrescriptionPO prescriptionPO = new PrescriptionPO();
        medicine.setPrescriptionList(List.of(prescriptionPO));
        prescriptionPO.setId(1L);
        prescriptionPO.setPrescriptionType(medicine);
        prescriptionPO.setPatient(patientPO);
        prescriptionPO.setFormedPrescription(MEDICINE_FORMED_PRESCRIPTION_2);
        doNothing().when(eventDAO).add(any());
        eventGenerationService.generateEvents(prescriptionPO);
        int countOfGeneratedEvents = 14;
        verify(eventDAO,times(countOfGeneratedEvents)).add((any()));
    }
}