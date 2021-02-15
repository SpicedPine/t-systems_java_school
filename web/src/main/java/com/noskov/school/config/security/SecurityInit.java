package com.noskov.school.config.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityInit  extends AbstractSecurityWebApplicationInitializer {
}
