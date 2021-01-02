package com.noskov.school.service.api;

import com.noskov.school.persistent.PatientPO;

import java.util.List;

public interface PatientService {
    List<PatientPO> getAllPatients();
    void add(PatientPO patient);
}
