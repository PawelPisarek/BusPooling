package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.config.IAuthenticationFacade;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.command.Comment.CreateComment;
import BusPooling.rest.aplication.command.DelayedTransport.CreateDelayedTransport;
import BusPooling.rest.aplication.command.DelayedTransport.UpdateDelayedTransport;
import BusPooling.rest.aplication.command.ICommand;
import BusPooling.rest.aplication.command.MyOffer.CreateMyOffer;
import BusPooling.rest.aplication.command.TransportOffer.CreateTransportOffer;
import BusPooling.rest.domain.DelayedTransport;
import BusPooling.rest.infrastructure.DAO.CommentDAO;
import BusPooling.rest.infrastructure.DAO.MyOfferDAO;
import BusPooling.rest.infrastructure.DAO.TransportOfferDAO;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.UnitOfWork;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;

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
    private IAuthenticationFacade authenticationFacade;


    public DelayedTransportCommandController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.delayedTransportQuery = context.getBean("getDelayedTransportQuery", DbalDelayedTransportQuery.class);
        this.unitOfWork = context.getBean("getUnitOfWork", UnitOfWork.class);
        this.authenticationFacade = context.getBean("getAuthenticationFacade", IAuthenticationFacade.class);
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

    @POST
    @Path("/{id}/my-offer")
    public Response addDelayedTransport(MyOfferDAO myOffer, @PathParam("id") String id) {
        ICommand command = new CreateMyOffer(myOffer,
                this.delayedTransportQuery.getByUuid(id),
                authenticationFacade.getAuthentication(),
                this.delayedTransportQuery.getAllUserFromMyOffers(delayedTransportQuery.getByUuid(id))); //TODO Tu jest źle bo powiadomi tylko tych użytkowników którzy złożyli oferte
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + myOffer.getId()).build();
        return Response.created(path).entity(myOffer).build();

    }

    @POST
    @Path("/{id}/transport-offer")
    public Response addTransportOffer(TransportOfferDAO transportOfferDAO, @PathParam("id") String id) {
        ICommand command = new CreateTransportOffer(transportOfferDAO, this.delayedTransportQuery.getByUuid(id), new ArrayList<>());
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + transportOfferDAO.getUuid()).build();
        return Response.created(path).entity(transportOfferDAO).build();
    }

    @POST
    @Path("/{id}/comment")
    public Response addCommand(CommentDAO commentDAO, @PathParam("id") String id) {
        ICommand command = new CreateComment(commentDAO, this.delayedTransportQuery.getByUuid(id), new ArrayList<>());
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + commentDAO.getUuid()).build();
        return Response.created(path).entity(commentDAO).build();
    }


}
