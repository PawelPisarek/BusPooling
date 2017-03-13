package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.*;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.aplication.command.MyOffer.UpdateMyOffer;
import BusPooling.rest.domain.MyOffer;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.DbalMyOfferQuery;
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
@Path("/my-offer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MyOfferCommandController {

    private ICommandBus commandBus;
    private DbalMyOfferQuery dbalMyOfferQuery;
    private UnitOfWork unitOfWork;


    public MyOfferCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.dbalMyOfferQuery = context.getBean("getMyOfferQuery", DbalMyOfferQuery.class);
        this.unitOfWork = context.getBean("getUnitOfWork", UnitOfWork.class);
    }



    @PUT
    public Response editDelayedTransport(MyOfferDAO delayedTransport) {

        this.unitOfWork.registerRepository(AppConfiguration.Repositories.MY_OFFER);
        ICommand command = new UpdateMyOffer(delayedTransport, this.dbalMyOfferQuery.getById(delayedTransport.getId()));
        this.commandBus.handle(command);
        this.unitOfWork.commit();

        URI path = UriBuilder.fromPath("/users/" + delayedTransport.getAuthor()).build();
        return Response.created(path).entity(delayedTransport).build();

    }
}
