package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.query.Person.PersonView;
import BusPooling.rest.infrastructure.DBAL.Person.IDbalPersonQuery;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by pawe on 3/16/17.
 */
@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonQueryController {
    private IDbalPersonQuery iPersonQuery;

    public PersonQueryController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.iPersonQuery = context.getBean("getPersonQuery", IDbalPersonQuery.class);
    }

    @GET
    @Path("/{email}")
    public PersonView getByEmail(@PathParam("email") String email) {

        return this.iPersonQuery.findByEmail(email);
    }
}
