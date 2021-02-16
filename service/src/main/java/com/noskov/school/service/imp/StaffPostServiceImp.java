package com.noskov.school.service.imp;

import com.noskov.school.dao.api.StaffPostDAO;
import com.noskov.school.persistent.StaffPostPO;
import com.noskov.school.service.api.StaffPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffPostServiceImp implements StaffPostService {
    @Autowired
    private StaffPostDAO staffPostDAO;

    @Override
    public List<StaffPostPO> getAllPosts() {
        return staffPostDAO.getAllPosts();
    }

    @Override
    public StaffPostPO getById(Long id) {
        return staffPostDAO.getById(id);
    }

    @Override
    public StaffPostPO getNurse() {
        return staffPostDAO.getById(2L);
    }

    @Override
    public StaffPostPO getPhysician() {
        return staffPostDAO.getById(1L);
    }
}
