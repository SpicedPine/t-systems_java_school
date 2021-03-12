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
import com.noskov.school.service.api.PrescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrescriptionServiceImp implements PrescriptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionServiceImp.class);

    private final PrescriptionDAO prescriptionDAO;

    private final PatientDAO patientDAO;

    private final ProcAndMedDAO procAndMedDAO;

    private final EventDAO eventDAO;

    private final EventGenerationService eventGenerationService;

    private final PrescriptionServiceConverter converter;

    @Autowired
    public PrescriptionServiceImp(PrescriptionDAO prescriptionDAO, PatientDAO patientDAO, ProcAndMedDAO procAndMedDAO, EventDAO eventDAO, EventGenerationService eventGenerationService, PrescriptionServiceConverter converter) {
        this.prescriptionDAO = prescriptionDAO;
        this.patientDAO = patientDAO;
        this.procAndMedDAO = procAndMedDAO;
        this.eventDAO = eventDAO;
        this.eventGenerationService = eventGenerationService;
        this.converter = converter;
    }

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

        LOGGER.info("Added prescription");
    }

    @Override
    public void delete(Long id) {
        PrescriptionPO prescriptionPO = prescriptionDAO.getById(id);
        PatientPO patientPO = prescriptionPO.getPatient();
        ProcedureAndMedicinePO therapy = prescriptionPO.getPrescriptionType();
        eventDAO.deleteByPatientAndTherapy(patientPO,therapy);
        prescriptionDAO.deleteById(id);

        LOGGER.info("Deleted prescription");
    }

    @Override
    public void update(PrescriptionDTO prescription, Long prescriptionId) {
        PrescriptionPO prescriptionPO = converter.convertToPO(prescription);
        prescriptionPO.setId(prescriptionId);
        prescriptionDAO.update(prescriptionPO);

        LOGGER.info("Updated prescription");
    }

    @Override
    public List<PrescriptionPO> getPrescriptionsByPatient(PatientPO patient) {
        return prescriptionDAO.getPrescriptionsByPatient(patient);
    }

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
        LOGGER.info("Edited prescription");
    }
}

