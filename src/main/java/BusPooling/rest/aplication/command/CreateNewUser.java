package BusPooling.rest.aplication.command;

import BusPooling.AppConfiguration;
import BusPooling.rest.domain.User;

/**
 * Created by pawe on 2/27/17.
 */
public class CreateNewUser implements ICommand {
    private User name;

    public CreateNewUser(User name) {
        this.name = name;
    }

    public User getName() {
        return name;
    }

    public void setName(User name) {
        this.name = name;
    }

    public String getKey() {
        return AppConfiguration.user;
    }
}
