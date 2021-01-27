package com.noskov.school.service.api;

import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface ProcAndMedService {
    List<ProcedureAndMedicinePO> getAllProceduresAndMedicines();
    List<ProcedureAndMedicinePO> getAllProcedures();
    List<ProcedureAndMedicinePO> getAllMedicines();
    void add(ProcedureAndMedicinePO procedureOrMedicine);
    ProcedureAndMedicinePO getById(Long id);
    ProcedureAndMedicinePO getByName(String name);
    void delete(ProcedureAndMedicinePO procedureOrMedicine);
    void update(ProcedureAndMedicinePO procedureOrMedicine);
}
