package com.noskov.school.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class JMSPublisher {
    private static String ALL_DAY_PROPERTY = "all_day";

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JMSPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendUpdateMessage(){
        jmsTemplate.send(session -> {
            TextMessage message = session.createTextMessage("schedule updated");
            message.setStringProperty(ALL_DAY_PROPERTY, "all day");
            return message;
        });
    }
}
