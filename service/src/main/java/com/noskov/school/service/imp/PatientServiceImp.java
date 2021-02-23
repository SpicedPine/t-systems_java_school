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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImp implements PatientService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Autowired
    private PatientServiceConverter converter;

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
        if (patientDAO.getBySocialNumber(socialNumber)!=null){
            return true;
        } else return false;
    }

    @Override
    public PatientPO getBySocialNumber(int socialNumber) {
        PatientPO patientPO = patientDAO.getBySocialNumber(socialNumber);
        if (patientPO!=null){
            return patientPO;
        } else throw new RuntimeException("during get patient by social number in service");
    }

    @Override
    public void release(Long id) {
        PatientPO patientPO = patientDAO.getById(id);
        patientPO.setStatus(PatientStatus.TREATED);
        eventDAO.deleteEventsFromNowForPatient(patientPO);
        prescriptionDAO.deletePrescriptionsByPatient(patientPO);
    }
}
