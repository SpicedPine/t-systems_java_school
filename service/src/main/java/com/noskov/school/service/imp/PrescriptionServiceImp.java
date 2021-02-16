package com.noskov.school.service.imp;

import com.noskov.school.converters.PrescriptionServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.dto.PrescriptionDTO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.EventGenerationService;
import com.noskov.school.service.api.EventService;
import com.noskov.school.service.api.PrescriptionService;
import com.noskov.school.service.api.ProcAndMedService;
import com.noskov.school.service.imp.prescription.PrescriptionBuilder;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrescriptionServiceImp implements PrescriptionService {
    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private ProcAndMedDAO procAndMedDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private EventGenerationService eventGenerationService;

    @Autowired
    private PrescriptionServiceConverter converter;

    @Override
    public List<PrescriptionDTO> getAll() {
        List<PrescriptionPO> prescriptionPOList = prescriptionDAO.getAllPrescriptions();
        return prescriptionPOList.stream()
                .map(p -> converter.convertToDTO(p))
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO getOne(Long id) {
        PrescriptionPO prescriptionPO = prescriptionDAO.getById(id);
        return converter.convertToDTO(prescriptionPO);
    }

    @Override
    public void add(PrescriptionDTO prescription, Long patientId) throws Exception {
        prescription.setPatient(patientDAO.getById(patientId));
        String name = prescription.getScratch().getTypeTherapyName();
        prescription.setProcOrMedicine(procAndMedDAO.getByName(name));
        PrescriptionPO prescriptionPO = converter.convertToPO(prescription);
        eventGenerationService.generateEvents(prescriptionPO);

        prescriptionDAO.add(prescriptionPO);
    }

    @Override
    public void delete(Long id) {
        PrescriptionPO prescriptionPO = prescriptionDAO.getById(id);
        PatientPO patientPO = prescriptionPO.getPatient();
        ProcedureAndMedicinePO therapy = prescriptionPO.getPrescriptionType();
        eventDAO.deleteByPatientAndTherapy(patientPO,therapy);
        prescriptionDAO.deleteById(id);
    }

    @Override
    public void update(PrescriptionDTO prescription, Long prescriptionId) {
        PrescriptionPO prescriptionPO = converter.convertToPO(prescription);
        prescriptionPO.setId(prescriptionId);
        prescriptionDAO.update(prescriptionPO);
    }

    /*@Override
    public PrescriptionDTO convertToDTO(PrescriptionPO prescriptionPO){
        PatientPO patient = prescriptionPO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionPO.getPrescriptionType();
        String formedPrescription = prescriptionPO.getFormedPrescription();
        PrescriptionScratch scratch = prescriptionBuilder.parsePrescription(formedPrescription);

        return new PrescriptionDTO(scratch, patient, procOrMed);
    }

    @Override
    public PrescriptionPO convertToPO(PrescriptionDTO prescriptionDTO){
        PatientPO patientPO = prescriptionDTO.getPatient();
        ProcedureAndMedicinePO procOrMed = prescriptionDTO.getProcOrMedicine();
        String formedPrescription = prescriptionBuilder.buildPrescription(prescriptionDTO.getScratch());

        return new PrescriptionPO(patientPO,procOrMed,formedPrescription);
    }*/

    @Override
    public List<PrescriptionPO> getPrescriptionsByPatient(PatientPO patient) {
        /*List<PrescriptionPO> poList = prescriptionDAO.getPrescriptionsByPatient(patient);
        return poList.stream().map(this::convertToDTO).collect(Collectors.toList());*/
        return prescriptionDAO.getPrescriptionsByPatient(patient);
    }

    /*@Override
    public void editMedicinePrescription(Long patientId, Long prescriptionId, PrescriptionDTO prescriptionDTO) throws Exception {
        editPrescription(patientId, prescriptionId, prescriptionDTO);
    }

    @Override
    public void editProcedurePrescription(Long patientId, Long prescriptionId, PrescriptionDTO prescriptionDTO) throws Exception {
        editPrescription(patientId, prescriptionId, prescriptionDTO);
    }*/

    @Override
    public void editPrescription(Long patientId, Long prescriptionId, PrescriptionDTO prescriptionDTO) throws Exception {
        PatientPO patientPO = patientDAO.getById(patientId);
        String therapyName = prescriptionDTO.getScratch().getTypeTherapyName();
        ProcedureAndMedicinePO oldProcOrMed = converter.convertToDTO(prescriptionDAO.getById(prescriptionId)).getProcOrMedicine();
        eventDAO.deleteByPatientAndTherapy(patientPO,oldProcOrMed);
        prescriptionDTO.setPatient(patientPO);
        prescriptionDTO.setProcOrMedicine(procAndMedDAO.getByName(therapyName));
        PrescriptionPO prescriptionPO= converter.convertToPO(prescriptionDTO);
        eventGenerationService.generateEvents(prescriptionPO);

        update(prescriptionDTO, prescriptionId);
    }
}

