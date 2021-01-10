package com.noskov.school.service.imp;

import com.noskov.school.dao.api.PrescriptionDAO;
import com.noskov.school.persistent.PrescriptionPO;
import com.noskov.school.service.api.EventGenerationService;
import com.noskov.school.service.imp.prescription.PrescriptionBuilder;
import com.noskov.school.service.imp.prescription.PrescriptionScratch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventGenerationServiceImp implements EventGenerationService {

    @Autowired
    private PrescriptionDAO prescriptionDAO;

    @Override
    public void generateEvents(PrescriptionPO prescription) throws Exception {
        PrescriptionBuilder builder = new PrescriptionBuilder();
        PrescriptionScratch scratch = builder.parsePrescription(prescription.getFormedPrescription());

    }
}
