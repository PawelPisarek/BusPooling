package BusPooling.rest.commandBus;

import BusPooling.rest.dao.User;

/**
 * Created by pawe on 2/27/17.
 */
public class CreateNewUser {
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
        return "user";
    }
}
