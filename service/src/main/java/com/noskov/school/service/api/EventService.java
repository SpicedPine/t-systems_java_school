package com.noskov.school.service.api;

import com.noskov.school.persistent.EventPO;
import java.util.List;

public interface EventService {
    List<EventPO> getAll();

    List<EventPO> getEventsForDay();

    List<EventPO> getEventsForHour();

    EventPO getOne(Long id);

    void add(EventPO event);

    void delete(Long id);

    void update(EventPO event);
}
