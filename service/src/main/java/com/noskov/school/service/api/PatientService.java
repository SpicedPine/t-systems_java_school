package com.noskov.school.service.api;

import com.noskov.school.persistent.PatientPO;

import java.util.List;

public interface PatientService {
    List<PatientPO> getAll();

    PatientPO getOne(Long id);

    void add(PatientPO patient);

    void delete(Long id);

    void update(PatientPO patient);
}
