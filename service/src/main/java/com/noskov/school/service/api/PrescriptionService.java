package com.noskov.school.service.api;


import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionDTO> getAll();

    PrescriptionDTO getOne(Long id);

    void add(PrescriptionDTO prescription);

    void delete(Long id);

    void update(PrescriptionDTO prescription, Long prescriptionId);

    PrescriptionPO convertToPO(PrescriptionDTO prescriptionDTO);

    PrescriptionDTO convertToDTO(PrescriptionPO prescriptionPO);

    List<PrescriptionPO> getPrescriptionsByPatient(PatientPO patient);
}
