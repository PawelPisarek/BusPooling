package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.command.TransportOffer.UpdateTransportOffer;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.DBAL.TransportOffer.IDbalTransportOfferQuery;
import BusPooling.rest.infrastructure.UnitOfWork;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by pawe on 3/3/17.
 */
@Path("/transport-offer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransportOfferCommandController {

    private ICommandBus commandBus;
    private IDbalTransportOfferQuery dbalTransportOfferQuery;
    private UnitOfWork unitOfWork;


    public TransportOfferCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.dbalTransportOfferQuery = context.getBean("getTransportOfferCachedQuery", IDbalTransportOfferQuery.class);
        this.unitOfWork = context.getBean("getUnitOfWork", UnitOfWork.class);
    }



    @PUT
    public Response editDelayedTransport(TransportOfferDAO transportOfferDAO) {

        this.unitOfWork.registerRepository(AppConfiguration.Repositories.TRANSPORT_OFFER);
        ICommand command = new UpdateTransportOffer(transportOfferDAO, this.dbalTransportOfferQuery.getByUuid(transportOfferDAO.getUuid()));
        this.commandBus.handle(command);
        this.unitOfWork.commit();

        URI path = UriBuilder.fromPath("/users/" + transportOfferDAO.getUuid()).build();
        return Response.created(path).entity(transportOfferDAO).build();

    }
}
