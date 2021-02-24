package com.noskov.school.service.imp;

import com.noskov.school.dao.api.ProcAndMedDAO;
import com.noskov.school.persistent.ProcedureAndMedicinePO;
import com.noskov.school.service.api.ProcAndMedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.List;

@Service
@Transactional
public class ProcAndMedServiceImp implements ProcAndMedService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcAndMedServiceImp.class);

    private final ProcAndMedDAO procAndMedDAO;

    @Autowired
    public ProcAndMedServiceImp(ProcAndMedDAO procAndMedDAO) {
        this.procAndMedDAO = procAndMedDAO;
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProceduresAndMedicines() {
        return procAndMedDAO.getAllProceduresAndMedicines();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllProcedures() {
        return procAndMedDAO.getAllProcedures();
    }

    @Override
    public List<ProcedureAndMedicinePO> getAllMedicines() {
        return procAndMedDAO.getAllMedicines();
    }

    @Override
    public void add(ProcedureAndMedicinePO procedureOrMedicine) {
        procAndMedDAO.add(procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getById(Long id) {
        return procAndMedDAO.getById(id);
    }

    @Override
    public void delete(ProcedureAndMedicinePO procedureOrMedicine) {
        procAndMedDAO.delete(procedureOrMedicine);
    }

    @Override
    public void update(ProcedureAndMedicinePO procedureOrMedicine) {
        procAndMedDAO.update(procedureOrMedicine);
    }

    @Override
    public ProcedureAndMedicinePO getByName(String name) {
        return procAndMedDAO.getByName(name);
    }
}
