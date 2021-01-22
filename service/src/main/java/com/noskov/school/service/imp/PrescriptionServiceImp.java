package com.noskov.school.service.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.service.api.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImp implements PrescriptionService {
    @Autowired
    PrescriptionDAO prescriptionDAO;

    @Override
    public List<PrescriptionPO> getAll() {
        return prescriptionDAO.getAllPrescriptions();
    }

    @Override
    public PrescriptionPO getOne(Long id) {
        return prescriptionDAO.getById(id);
    }

    @Override
    public void add(PrescriptionPO prescription) {
        prescriptionDAO.add(prescription);
    }

    @Override
    public void delete(Long id) {
        prescriptionDAO.deleteById(id);
    }

    @Override
    public void update(PrescriptionPO prescription) {
        prescriptionDAO.update(prescription);
    }
}

