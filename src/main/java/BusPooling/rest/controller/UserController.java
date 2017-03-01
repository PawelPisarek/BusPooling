package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.aplication.query.User.IUserQuery;
import BusPooling.rest.aplication.query.User.UserView.UserView;
import BusPooling.rest.commandBus.CreateNewUser;
import BusPooling.rest.commandBus.ICommand;
import BusPooling.rest.commandBus.ICommandBus;
import BusPooling.rest.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {


    private ICommandBus commandBus;
    private IUserQuery userQuery;


    public UserController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.commandBus = context.getBean("getCommandBus", ICommandBus.class);
        this.userQuery = context.getBean("getUserQuery", IUserQuery.class);
    }

    @GET
    public List<UserView> getUsers() {
        return this.userQuery.getAll();
    }

    @POST
    public Response registerUser(User user) {

        ICommand command = new CreateNewUser(user);
        this.commandBus.handle(command);

        URI path = UriBuilder.fromPath("/users/" + user.getName()).build();
        return Response.created(path).entity(user).build();

//        return Response.status(Response.Status.CREATED)
//                .build();
    }


}
