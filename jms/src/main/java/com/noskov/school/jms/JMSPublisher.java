package com.noskov.school.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class JMSPublisher {
    private static String UPDATE_ALL_DAY = "update all day";

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JMSPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendUpdateMessage(){
        jmsTemplate.send(session -> {
            TextMessage message = session.createTextMessage("schedule updated");
            message.setStringProperty("All_day", UPDATE_ALL_DAY );
            return message;
        });
    }
}
