package com.noskov.school.service.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcAndMedServiceImpTest {
    private ProcAndMedServiceImp procAndMedService;
    private ProcAndMedDAO procAndMedDAO;

    @BeforeEach
    private void init(){
        procAndMedDAO = mock(ProcAndMedDAO.class);

        procAndMedService = new ProcAndMedServiceImp(procAndMedDAO);
    }

    @Test
    void getAllProceduresAndMedicines() {
        List<ProcedureAndMedicinePO> list = new ArrayList<>();
        when(procAndMedDAO.getAllProceduresAndMedicines()).thenReturn(list);
        assertEquals(procAndMedService.getAllProceduresAndMedicines(), list);
    }

    @Test
    void getAllProcedures() {
        List<ProcedureAndMedicinePO> list = new ArrayList<>();
        when(procAndMedDAO.getAllMedicines()).thenReturn(list);
        assertEquals(procAndMedService.getAllMedicines(), list);
    }

    @Test
    void getAllMedicines() {
        List<ProcedureAndMedicinePO> list = new ArrayList<>();
        when(procAndMedDAO.getAllProcedures()).thenReturn(list);
        assertEquals(procAndMedService.getAllProcedures(), list);
    }

    @Test
    void add() {
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);
        doNothing().when(procAndMedDAO).add(procedureAndMedicinePO);
        procAndMedService.add(procedureAndMedicinePO);
        verify(procAndMedDAO,times(1)).add(procedureAndMedicinePO);
        verifyNoMoreInteractions(procedureAndMedicinePO);
    }

    @Test
    void getById() {
        Long id = 1L;
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);
        when(procAndMedDAO.getById(id)).thenReturn(procedureAndMedicinePO);
        assertEquals(procAndMedService.getById(id), procedureAndMedicinePO);
    }

    @Test
    void delete() {
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);
        doNothing().when(procAndMedDAO).delete(procedureAndMedicinePO);
        procAndMedService.delete(procedureAndMedicinePO);
        verify(procAndMedDAO, times(1)).delete(procedureAndMedicinePO);
        verifyNoMoreInteractions(procAndMedDAO);
    }

    @Test
    void update() {
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);
        doNothing().when(procAndMedDAO).update(procedureAndMedicinePO);
        procAndMedService.update(procedureAndMedicinePO);
        verify(procAndMedDAO, times(1)).update(procedureAndMedicinePO);
        verifyNoMoreInteractions(procAndMedDAO);
    }

    @Test
    void getByName() {
        String name = "";
        ProcedureAndMedicinePO procedureAndMedicinePO = mock(ProcedureAndMedicinePO.class);
        when(procAndMedDAO.getByName(name)).thenReturn(procedureAndMedicinePO);
        assertEquals(procAndMedService.getByName(name), procAndMedDAO.getByName(name));
    }
}