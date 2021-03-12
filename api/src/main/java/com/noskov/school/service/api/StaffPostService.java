package com.noskov.school.service.api;

import com.noskov.school.persistent.StaffPostPO;
import java.util.List;

public interface StaffPostService {
    List<StaffPostPO> getAllPosts();
    StaffPostPO getById(Long id);
    StaffPostPO getNurse();
    StaffPostPO getPhysician();
}
