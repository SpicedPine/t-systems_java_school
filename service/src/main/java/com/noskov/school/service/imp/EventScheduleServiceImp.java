package com.noskov.school.service.imp;

import com.noskov.school.jms.JMSPublisher;
import com.noskov.school.service.api.EventScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventScheduleServiceImp implements EventScheduleService {

    private final JMSPublisher jmsPublisher;

    @Autowired
    public EventScheduleServiceImp(JMSPublisher jmsPublisher) {
        this.jmsPublisher = jmsPublisher;
    }


    @Override
    public void updateSchedule() {
        jmsPublisher.sendUpdateMessage();
    }
}
