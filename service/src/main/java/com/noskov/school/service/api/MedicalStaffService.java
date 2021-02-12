package com.noskov.school.service.api;

import com.noskov.school.persistent.MedicalStaffPO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MedicalStaffService extends UserDetailsService {
    List<MedicalStaffPO> getAllStaff();
    void add(MedicalStaffPO staff);
    void save(MedicalStaffPO staff);
    boolean savePhysician(MedicalStaffPO staff);
    boolean saveNurse(MedicalStaffPO staff);
    MedicalStaffPO getById(Long id);
    void delete(MedicalStaffPO staff);
    void update(MedicalStaffPO staff);
    List<MedicalStaffPO> getAllPhysicians();
}
