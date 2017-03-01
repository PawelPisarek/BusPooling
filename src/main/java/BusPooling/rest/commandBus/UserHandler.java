package BusPooling.rest.commandBus;

import BusPooling.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pawe on 2/27/17.
 */
public class UserHandler implements IHandleCommand<CreateNewUser> {


    private UserService userService;


    public UserHandler(UserService userService) {
        this.userService = userService;
    }


    public void handle(CreateNewUser command) {


        this.userService.addUser(command.getName());


    }


}
