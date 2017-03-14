package BusPooling.config;

import BusPooling.configurations.UsersController;
import BusPooling.rest.controller.*;
import BusPooling.rest.exception.CustomExceptionMapper;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        register(LoggingFilter.class);
        register(UsersController.class);
        register(DelayedTransportQueryController.class);
        register(TransportOfferCommandController.class);
        register(MyOfferCommandController.class);
        register(DelayedTransportCommandController.class);
        register(UserController.class);
        register(CustomExceptionMapper.class);
    }
}
