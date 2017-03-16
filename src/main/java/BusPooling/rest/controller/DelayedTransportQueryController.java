package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportDetailView;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.aplication.query.TransportView.TransportOfferView;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.DBAL.TransportOffer.IDbalTransportOfferQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by pawe on 3/3/17.
 */
@Path("/delayed-transport")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DelayedTransportQueryController {

    private ICommandBus commandBus;
    private DbalDelayedTransportQuery delayedTransportQuery;


    public DelayedTransportQueryController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.delayedTransportQuery = context.getBean("getDelayedTransportQuery", DbalDelayedTransportQuery.class);
    }

    @GET
    public List<DelayedTransportView> getUsers() {
        return this.delayedTransportQuery.getAll();
    }

    @GET
    @Path("/{id}/my-offer")
    public List<MyOfferView> getMyOffer(@PathParam("id") String id) {
        return this.delayedTransportQuery.getMyOffers(this.delayedTransportQuery.getByUuid(id));
    }

    @GET
    @Path("/{id}/transport-offer")
    public List<TransportOfferView> getTransportOffer(@PathParam("id") String id) {
        return this.delayedTransportQuery.getTransportOffers(this.delayedTransportQuery.getByUuid(id));
    }

    @GET
    @Path("/{id}")
    public DelayedTransportDetailView getDetailView(@PathParam("id") String id) {
        return this.delayedTransportQuery.getByUuidDetail(id);
    }
}
