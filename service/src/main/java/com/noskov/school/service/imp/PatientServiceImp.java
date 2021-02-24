package com.noskov.school.service.imp;

import com.noskov.school.converters.PatientServiceConverter;
import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.dto.PatientProfileDTO;
import com.noskov.school.enums.PatientStatus;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.service.api.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImp implements PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImp.class);


    private final EventDAO eventDAO;

    private final PatientDAO patientDAO;

    private final PrescriptionDAO prescriptionDAO;

    private final PatientServiceConverter converter;

    @Autowired
    public PatientServiceImp(EventDAO eventDAO, PatientDAO patientDAO, PrescriptionDAO prescriptionDAO, PatientServiceConverter converter) {
        this.eventDAO = eventDAO;
        this.patientDAO = patientDAO;
        this.prescriptionDAO = prescriptionDAO;
        this.converter = converter;
    }

    @Override
    public List<PatientPO> getAll() {
        return patientDAO.getAllPatients();
    }

    @Override
    public PatientPO getOne(Long id) {
        return patientDAO.getById(id);
    }

    @Override
    public void add(PatientPO patient) {
        patientDAO.add(patient);
    }

    @Override
    public void delete(Long id) {
        patientDAO.deleteById(id);
    }

    @Override
    public void update(PatientPO patient) {
        patientDAO.update(patient);
    }

    @Override
    public PatientProfileDTO getPatientProfile(Long id){
        PatientPO patientPO = getOne(id);
        List<PrescriptionPO> prescriptionPOList = prescriptionDAO.getPrescriptionsByPatient(patientPO);
        LOGGER.info("Got patient's profile");
        return converter.patientProfile(patientPO,prescriptionPOList);
    }

    @Override
    public void addIfNotExist(PatientPO patientPO) {
        int socialNumber = patientPO.getSocialNumber();
        if(!checkExistenceBySocialNumber(socialNumber)){
            add(patientPO);
        }
    }

    @Override
    public boolean checkExistenceBySocialNumber(int socialNumber) {
        LOGGER.info("Checking for existence by social number");
        if (patientDAO.getBySocialNumber(socialNumber)!=null){
            return true;
        } else return false;
    }

    @Override
    public PatientPO getBySocialNumber(int socialNumber) {
        PatientPO patientPO = patientDAO.getBySocialNumber(socialNumber);
        if (patientPO!=null){
            return patientPO;
        } else {
            LOGGER.error("Couldn't found patient by social number");
            throw new RuntimeException("during get patient by social number in service");
        }
    }

    @Override
    public void release(Long id) {
        PatientPO patientPO = patientDAO.getById(id);
        patientPO.setStatus(PatientStatus.TREATED);
        eventDAO.deleteEventsFromNowForPatient(patientPO);
        prescriptionDAO.deletePrescriptionsByPatient(patientPO);
        LOGGER.error("Release patient by id");
    }
}
