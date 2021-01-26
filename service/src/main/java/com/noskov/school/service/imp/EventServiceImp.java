package com.noskov.school.service.imp;

import com.noskov.school.dao.api.EventDAO;
import com.noskov.school.persistent.EventPO;
import com.noskov.school.service.api.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {
    @Autowired
    EventDAO eventDAO;

    @Override
    public List<EventPO> getAll() {
        return eventDAO.getAllEvents();
    }

    @Override
    public List<EventPO> getEventsForDay() {
        return null;
    }

    @Override
    public List<EventPO> getEventsForHour() {
        return null;
    }

    @Override
    public EventPO getOne(Long id) {
        return eventDAO.getById(id);
    }

    @Override
    public void add(EventPO event) {
        eventDAO.add(event);
    }

    @Override
    public void delete(Long id) {
        eventDAO.deleteById(id);
    }

    @Override
    public void update(EventPO event) {
        eventDAO.update(event);
    }
}
