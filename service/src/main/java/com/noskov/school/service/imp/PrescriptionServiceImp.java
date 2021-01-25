package com.noskov.school.service.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.PrescriptionService;
import com.noskov.school.service.imp.prescription.PrescriptionBuilder;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImp implements PrescriptionService {
    @Autowired
    PrescriptionDAO prescriptionDAO;

    @Autowired
    PrescriptionBuilder prescriptionBuilder;

    @Override
    public List<PrescriptionDTO> getAll() {
        List<PrescriptionPO> prescriptionPOList = prescriptionDAO.getAllPrescriptions();
        return prescriptionPOList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO getOne(Long id) {
        PrescriptionPO prescriptionPO = prescriptionDAO.getById(id);
        return convertToDTO(prescriptionPO);
    }

    @Override
    public void add(PrescriptionDTO prescription) {
        PrescriptionPO prescriptionPO = convertToPO(prescription);
        prescriptionDAO.add(prescriptionPO);
    }

    @Override
    public void delete(Long id) {
        prescriptionDAO.deleteById(id);
    }

    @Override
    public void update(PrescriptionDTO prescription) {
        prescriptionDAO.update(convertToPO(prescription));
    }

    private PrescriptionDTO convertToDTO(PrescriptionPO prescriptionPO){
        PatientPO patient = prescriptionPO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionPO.getPrescriptionType();
        String formedPrescription = prescriptionPO.getFormedPrescription();
        PrescriptionScratch scratch = prescriptionBuilder.parsePrescription(formedPrescription);

        return new PrescriptionDTO(scratch, patient, procOrMed);
    }

    private PrescriptionPO convertToPO(PrescriptionDTO prescriptionDTO){
        PatientPO patientPO = prescriptionDTO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionDTO.getProcOrMedicine();
        String formedPrescription = prescriptionBuilder.buildPrescription(prescriptionDTO.getScratch());

        return new PrescriptionPO(patientPO,procOrMed,formedPrescription);
    }
}

