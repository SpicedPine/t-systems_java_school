package com.noskov.school.service.imp;

import com.noskov.school.dao.api.MedicalStaffDAO;
import com.noskov.school.dao.api.StaffPostDAO;
import com.noskov.school.enums.Role;
import com.noskov.school.persistent.MedicalStaffPO;
import com.noskov.school.persistent.PatientPO;
import com.noskov.school.persistent.StaffPostPO;
import com.noskov.school.service.api.MedicalStaffService;
import com.noskov.school.service.api.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicalStaffServiceImp.class);

    private final PatientService patientService;

    private final MedicalStaffDAO medicalStaffDAO;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MedicalStaffServiceImp(PatientService patientService, MedicalStaffDAO medicalStaffDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientService = patientService;
        this.medicalStaffDAO = medicalStaffDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<MedicalStaffPO> getAllStaff() {
        return medicalStaffDAO.getAllStaff();
    }

    @Override
    public void add(MedicalStaffPO staff) {
        staff.setPassword(bCryptPasswordEncoder.encode(staff.getPassword()));
        medicalStaffDAO.add(staff);
        LOGGER.info("Add staff");
    }

    @Override
    public MedicalStaffPO getById(Long id) {
        return medicalStaffDAO.getById(id);
    }

    @Override
    public void delete(MedicalStaffPO staff) {
        medicalStaffDAO.delete(staff);
        LOGGER.info("delete staff");
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
        LOGGER.info("load staff by email");
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
        LOGGER.info("Save physician");
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
        LOGGER.info("Save nurse");
        return true;
    }

    @Override
    public void addPatientToPhysician(PatientPO patientPO, MedicalStaffPO physician) {
        int socialNumber = patientPO.getSocialNumber();
        patientService.addIfNotExist(patientPO);
        PatientPO patient = patientService.getBySocialNumber(socialNumber);
        patient.getPhysicians().add(physician);
        physician.getPatients().add(patient);

        LOGGER.info("Added patient to physician");
    }

    @Override
    public boolean checkExistence(MedicalStaffPO staffPO) {
        MedicalStaffPO staffPOFromDB = medicalStaffDAO.getByEmail(staffPO.getEmail());

        if (staffPOFromDB!=null){
            return false;
        } else {
            return true;
        }
    }
}
