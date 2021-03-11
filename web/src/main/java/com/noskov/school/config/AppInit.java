package com.noskov.school.config;


import com.noskov.school.dao.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppInit.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOGGER.info("Getting root config class");
        return new Class<?>[]{
                AppConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOGGER.info("Getting servlet config class");
        return new Class<?>[]{
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        var dispatcher = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);
        LOGGER.info("Enable to throw exceptions if handler for page not found");
        return dispatcher;
    }
}
