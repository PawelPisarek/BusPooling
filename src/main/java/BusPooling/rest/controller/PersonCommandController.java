package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.command.Person.RegisterPerson;
import BusPooling.rest.infrastructure.DAO.RegisterPersonDAO;
import BusPooling.rest.infrastructure.DBAL.Person.IDbalPersonQuery;
import BusPooling.rest.infrastructure.UnitOfWork;
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
@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonCommandController {

    private ICommandBus commandBus;
    private IDbalPersonQuery dbalTransportOfferQuery;
    private UnitOfWork unitOfWork;


    public PersonCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.dbalTransportOfferQuery = context.getBean("getPersonQuery", IDbalPersonQuery.class);
        this.unitOfWork = context.getBean("getUnitOfWork", UnitOfWork.class);
    }


    @POST
    public Response registerPerson(RegisterPersonDAO registerPersonDAO){
        ICommand command = new RegisterPerson(registerPersonDAO);
        this.commandBus.handle(command);
        final URI uriBuilder = UriBuilder.fromPath("/person").build();
        return Response.created(uriBuilder).build();
    }
}
