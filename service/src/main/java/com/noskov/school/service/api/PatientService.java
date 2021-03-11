package com.noskov.school.service.api;

import com.noskov.school.dto.PatientProfileDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import java.util.List;

public interface PatientService {
    List<PatientPO> getAll();

    PatientPO getOne(Long id);

    void add(PatientPO patient);

    void delete(Long id);

    void update(PatientPO patient);

    PatientProfileDTO getPatientProfile(Long id);

    void addIfNotExist(PatientPO patientPO);

    boolean checkExistenceBySocialNumber(int socialNumber);

    PatientPO getBySocialNumber(int socialNumber);

    void release(Long id);
}
