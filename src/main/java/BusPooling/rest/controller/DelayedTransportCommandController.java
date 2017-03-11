package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.CreateDelayedTransport;
import BusPooling.rest.aplication.command.CreateNewUser;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * Created by pawe on 3/3/17.
 */
@Path("/delayed-transport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DelayedTransportCommandController {

    private ICommandBus commandBus;
    private DbalDelayedTransportQuery delayedTransportQuery;


    public DelayedTransportCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.delayedTransportQuery = context.getBean("getDelayedTransportQuery", DbalDelayedTransportQuery.class);
    }


    @POST
    public Response addDelayedTransport(DelayedTransport delayedTransport) {

        ICommand command = new CreateDelayedTransport(delayedTransport);
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + delayedTransport.getFrom()).build();
        return Response.created(path).entity(delayedTransport).build();

    }
}
