package com.noskov.school.dao.api;


import com.noskov.school.persistent.PrescriptionPO;

import java.util.List;

public interface PrescriptionDAO {
    List<PrescriptionPO> getAllPrescriptions();
    void add(PrescriptionPO prescription);
    PrescriptionPO getById(Long id);
    void delete(PrescriptionPO prescription);
    void update(PrescriptionPO prescription);
    void deleteById(Long id);
}
