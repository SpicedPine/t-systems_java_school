package com.noskov.school.jms.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import java.util.Collections;

@Configuration
@EnableJms
public class JMSConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(JMSConfig.class);

    private static final String MESSAGE_BROKER_URL = "tcp://active-mq:61616";
    //private static final String MESSAGE_BROKER_URL = "tcp://localhost:61616";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String TOPIC_NAME = "eventsTopic";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(MESSAGE_BROKER_URL);
        connectionFactory.setUserName(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setTrustedPackages(Collections
                .singletonList("com.noskov.school"));

        LOGGER.info("connectionFactory to activeMQ was created");
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate;
        jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDefaultDestinationName(TOPIC_NAME);

        LOGGER.info("JMS template was created");
        return jmsTemplate;
    }

}
