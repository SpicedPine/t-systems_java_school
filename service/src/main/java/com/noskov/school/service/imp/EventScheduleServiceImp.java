package com.noskov.school.service.imp;

import com.noskov.school.jms.JMSPublisher;
import com.noskov.school.service.api.EventScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventScheduleServiceImp implements EventScheduleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventScheduleServiceImp.class);

    private final JMSPublisher jmsPublisher;

    @Autowired
    public EventScheduleServiceImp(JMSPublisher jmsPublisher) {
        this.jmsPublisher = jmsPublisher;
    }


    @Override
    public void updateSchedule() {
        LOGGER.info("Sending update message from service...");
        jmsPublisher.sendUpdateMessage();
    }
}
