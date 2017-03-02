package BusPooling.config;

import BusPooling.rest.controller.UserController;
import BusPooling.rest.exception.CustomExceptionMapper;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        register(LoggingFilter.class);
        register(UserController.class);
        register(CustomExceptionMapper.class);
    }
}
