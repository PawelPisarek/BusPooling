package BusPooling.configurations;



import BusPooling.configurations.data.User;
import BusPooling.configurations.repositories.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafal on 3/20/16.
 */
@Component
@Path("/api/users")
public class UsersController {
    @Autowired
    UsersDatabase usersDatabase;

    @GET
    @Path("/1")
    @Produces("application/json")
    public User getUserById() {
        return new User("1", "test", "test123", "password");
    }

    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {
//        return usersDatabase.getUsers();
        List<User> users = new ArrayList<>();
        users.add(new User("1", "test", "test", "test"));
        users.add(new User("2", "test2", "test2", "test2"));
        return users;
    }

    @POST
    public void addUser() {
        User user = new User("5", "someName", "someLogin", "somePassword");
        usersDatabase.addUser(user);
        System.out.println("test");
    }
}
