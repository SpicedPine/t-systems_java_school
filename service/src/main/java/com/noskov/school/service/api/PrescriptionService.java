package com.noskov.school.service.api;

import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionPO> getAll();

    PrescriptionPO getOne(Long id);

    void add(PrescriptionPO prescription);

    void delete(Long id);

    void update(PrescriptionPO prescription);
}
