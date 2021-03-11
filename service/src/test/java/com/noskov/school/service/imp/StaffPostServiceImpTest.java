package com.noskov.school.service.imp;

import com.noskov.school.dao.api.StaffPostDAO;
import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.persistent.StaffPostPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StaffPostServiceImpTest {

    private StaffPostServiceImp staffPostService;
    private StaffPostDAO staffPostDAO;

    @BeforeEach
    private void init(){
        staffPostDAO = mock(StaffPostDAO.class);

        staffPostService = new StaffPostServiceImp(staffPostDAO);
    }

    @Test
    void getAllPosts() {
        List<StaffPostPO> list = new ArrayList<>();
        when(staffPostDAO.getAllPosts()).thenReturn(list);
        assertEquals(staffPostService.getAllPosts(), list);
    }

    @Test
    void getById() {
        Long id = 1L;
        StaffPostPO staffPostPO = mock(StaffPostPO.class);
        when(staffPostDAO.getById(id)).thenReturn(staffPostPO);
        assertEquals(staffPostService.getById(id), staffPostPO);
    }

    @Test
    void getNurse() {
        when(staffPostDAO.getById(2L)).thenReturn(null);
        staffPostService.getNurse();
        verify(staffPostDAO,times(1)).getById(2L);
        verifyNoMoreInteractions(staffPostDAO);
    }

    @Test
    void getPhysician() {
        when(staffPostDAO.getById(1L)).thenReturn(null);
        staffPostService.getPhysician();
        verify(staffPostDAO,times(1)).getById(1L);
        verifyNoMoreInteractions(staffPostDAO);
    }
}