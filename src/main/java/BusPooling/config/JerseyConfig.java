package BusPooling.config;

import BusPooling.configurations.UsersController;
import BusPooling.rest.controller.*;
import BusPooling.rest.exception.CustomExceptionMapper;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        register(LoggingFilter.class);
        registerEndpoints();
    }

    private void registerEndpoints() {

        register(WadlResource.class);
        register(UsersController.class);
        register(DelayedTransportQueryController.class);
        register(TransportOfferCommandController.class);
        register(MyOfferCommandController.class);
        register(DelayedTransportCommandController.class);
        register(UserController.class);
        register(PersonCommandController.class);
        register(PersonQueryController.class);
        register(CustomExceptionMapper.class);
    }
}
