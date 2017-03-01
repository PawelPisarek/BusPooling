package BusPooling.rest.controller;

import BusPooling.AppConfiguration;
import BusPooling.rest.dao.User;
import BusPooling.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    private UserService userService;


    public UserController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        this.userService = context.getBean("getUserService", UserService.class);
    }

    @GET
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @POST
    public Response registerUser(User user){

        this.userService.addUser(user);

        URI path = UriBuilder.fromPath("/users/" + user.getName()).build();
        return Response.created(path).entity(user).build();

//        return Response.status(Response.Status.CREATED)
//                .build();
    }


}
