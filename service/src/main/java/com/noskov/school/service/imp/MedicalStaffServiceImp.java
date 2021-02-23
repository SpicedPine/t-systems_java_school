package com.noskov.school.service.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.dao.api.StaffPostDAO;
import com.noskov.school.enums.Role;
import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.StaffPostPO;
import com.noskov.school.service.api.MedicalStaffService;
import com.noskov.school.service.api.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicalStaffServiceImp implements MedicalStaffService {
    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalStaffDAO medicalStaffDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        return medicalStaffDAO.getAllStaff();
    }

    @Override
    public void add(MedicalStaffPO staff) {
        staff.setPassword(bCryptPasswordEncoder.encode(staff.getPassword()));
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
                .filter(m -> m.getPost().toString().equals(Role.PHYSITIAN.toString()))
                .collect(Collectors.toList());

        return medicalStaffPOList;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return medicalStaffDAO.getByEmail(s);
    }

    @Override
    public boolean savePhysician(MedicalStaffPO staff) {
        MedicalStaffPO staffPOFromDB = medicalStaffDAO.getByEmail(staff.getEmail());

        if (staffPOFromDB!=null){
            return false;
        }

        staff.setPost(new StaffPostPO(1L, Role.PHYSITIAN));
        add(staff);
        return true;
    }

    @Override
    public boolean saveNurse(MedicalStaffPO staff) {
        MedicalStaffPO staffPOFromDB = medicalStaffDAO.getByEmail(staff.getEmail());

        if (staffPOFromDB!=null){
            return false;
        }

        staff.setPost(new StaffPostPO(2L, Role.NURSE));
        add(staff);
        return true;
    }

    @Override
    public void addPatientToPhysician(PatientPO patientPO, MedicalStaffPO physician) {
        int socialNumber = patientPO.getSocialNumber();
        patientService.addIfNotExist(patientPO);
        PatientPO patient = patientService.getBySocialNumber(socialNumber);
        patient.getPhysicians().add(physician);
        physician.getPatients().add(patient);
    }
}
