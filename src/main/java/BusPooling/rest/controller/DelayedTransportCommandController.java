package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.CreateDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.command.UpdateDelayedTransport;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by pawe on 3/3/17.
 */
@Path("/delayed-transport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DelayedTransportCommandController {

    private ICommandBus commandBus;
    private DbalDelayedTransportQuery delayedTransportQuery;
    private UnitOfWork unitOfWork;


    public DelayedTransportCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.delayedTransportQuery = context.getBean("getDelayedTransportQuery", DbalDelayedTransportQuery.class);
        this.unitOfWork = context.getBean("getUnitOfWork", UnitOfWork.class);
    }


    @POST
    public Response addDelayedTransport(DelayedTransport delayedTransport) {

        ICommand command = new CreateDelayedTransport(delayedTransport);
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + delayedTransport.getFrom()).build();
        return Response.created(path).entity(delayedTransport).build();

    }

    @PUT
    public Response editDelayedTransport(DelayedTransport delayedTransport) {

        this.unitOfWork.registerRepository(AppConfiguration.Repositories.DELAYED_TRANSPORT);
        ICommand command = new UpdateDelayedTransport(delayedTransport, this.delayedTransportQuery.getById(delayedTransport.getId()));
        this.commandBus.handle(command);
        this.unitOfWork.commit();

        URI path = UriBuilder.fromPath("/users/" + delayedTransport.getFrom()).build();
        return Response.created(path).entity(delayedTransport).build();

    }
}
