package com.noskov.school.dao.api;

import com.noskov.school.persistent.StaffPostPO;

import java.util.List;

public interface StaffPostDAO {
    List<StaffPostPO> getAllPosts();
    StaffPostPO getById(Long id);
}
