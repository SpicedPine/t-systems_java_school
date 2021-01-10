package com.noskov.school.dao.api;

import com.noskov.school.persistent.EventPO;

import java.util.List;

public interface EventDAO {
    List<EventPO> getAllEvents();
    void add(EventPO eventPO);
    EventPO getById(Long id);
    void delete(EventPO event);
    void update(EventPO event);
}
