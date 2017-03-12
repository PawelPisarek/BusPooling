package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.ICommandBus;
import BusPooling.rest.aplication.query.DelayedTransportView.DelayedTransportView;
import BusPooling.rest.aplication.query.MyOfferView.MyOfferView;
import BusPooling.rest.infrastructure.DbalDelayedTransportQuery;
import BusPooling.rest.infrastructure.DbalMyOfferQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    private DbalMyOfferQuery dbalMyOfferQuery;


    public DelayedTransportQueryController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.delayedTransportQuery = context.getBean("getDelayedTransportQuery", DbalDelayedTransportQuery.class);
        this.dbalMyOfferQuery = context.getBean("getMyOfferQuery", DbalMyOfferQuery.class);
    }

    @GET
    public List<DelayedTransportView> getUsers() {
        return this.delayedTransportQuery.getAll();
    }

    @GET
    @Path("/my-offer")
    public List<MyOfferView> getMyOffer() {
        return this.dbalMyOfferQuery.getAll();
    }
}
