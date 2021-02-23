package com.noskov.school.dao.api;

import com.noskov.school.persistent.PatientPO;

import java.util.List;

public interface PatientDAO {
    List<PatientPO> getAllPatients();
    void add(PatientPO patient);
    PatientPO getById(Long id);
    void deleteById(Long id);
    void delete(PatientPO patient);
    void update(PatientPO patient);

    PatientPO getBySocialNumber(int socialNumber);
}
