package com.noskov.school.dao.api;

import com.noskov.school.persistent.MedicalStaffPO;

import java.util.List;

public interface MedicalStaffDAO {
    List<MedicalStaffPO> getAllStaff();
    void add(MedicalStaffPO staff);
    MedicalStaffPO getById(Long id);
    void delete(MedicalStaffPO staff);
    void update(MedicalStaffPO staff);
}
