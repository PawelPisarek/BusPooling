package BusPooling.rest.domain;

/**
 * Created by pawe on 2/27/17.
 */

public class User {

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
