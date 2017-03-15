package BusPooling.configurations;



import BusPooling.configurations.data.User;
import BusPooling.configurations.repositories.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rafal on 3/20/16.
 */
@Component
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersController {
    @Autowired
    UsersDatabase usersDatabase;

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") String id) {
        return usersDatabase.getUser(id);
    }

    @GET
    public List<User> getAllUsers() {
        return usersDatabase.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        usersDatabase.addUser(user);
        return Response.status(201).build();
    }

    @Path("/{userId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") String id, User user) {
        usersDatabase.updateUser(id, user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteMeeting(@PathParam("userId") String Id) {
        usersDatabase.deleteUser(Id);
        return Response.ok().build();
    }
}
