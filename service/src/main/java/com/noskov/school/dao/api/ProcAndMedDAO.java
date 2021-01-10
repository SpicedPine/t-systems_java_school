package com.noskov.school.dao.api;

import com.noskov.school.persistent.ProcedureAndMedicinePO;

import java.util.List;

public interface ProcAndMedDAO {
    List<ProcedureAndMedicinePO> getAllProceduresAndMedicines();
    void add(ProcedureAndMedicinePO procedureOrMedicine);
    ProcedureAndMedicinePO getById(Long id);
    void delete(ProcedureAndMedicinePO procedureOrMedicine);
    void update(ProcedureAndMedicinePO procedureOrMedicine);
}
