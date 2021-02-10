package com.noskov.school.service.imp;

import com.noskov.school.dao.api.PatientDAO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImp implements PatientService {
    @Autowired
    PatientDAO patientDAO;

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
}
