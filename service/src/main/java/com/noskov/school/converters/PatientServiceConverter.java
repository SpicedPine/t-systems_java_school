package com.noskov.school.converters;

import com.noskov.school.dto.PatientProfileDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientServiceConverter {
    public PatientProfileDTO patientProfile(PatientPO patientPO, List<PrescriptionPO> prescriptionPOList){
        return new PatientProfileDTO(patientPO,prescriptionPOList);
    }
}
