package com.noskov.school.service.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.enums.StaffPost;
import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.service.api.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalStaffServiceImp implements MedicalStaffService {
    @Autowired
    MedicalStaffDAO medicalStaffDAO;

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        return medicalStaffDAO.getAllStaff();
    }

    @Override
    public void add(MedicalStaffPO staff) {
        medicalStaffDAO.add(staff);
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        return medicalStaffDAO.getById(id);
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        medicalStaffDAO.delete(staff);
    }

    @Override
    public void update(MedicalStaffPO staff) {
        medicalStaffDAO.update(staff);
    }

    @Override
    public List<MedicalStaffPO> getAllPhysicians() {
        List<MedicalStaffPO> medicalStaffPOList = medicalStaffDAO.getAllStaff();
        medicalStaffPOList = medicalStaffPOList.stream()
                .filter(m -> m.getPost().equals(StaffPost.PHYSITIAN))
                .collect(Collectors.toList());

        return medicalStaffPOList;
    }
}
