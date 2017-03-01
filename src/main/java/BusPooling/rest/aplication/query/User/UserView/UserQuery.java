package BusPooling.rest.aplication.query.User.UserView;

/**
 * Created by pawe on 3/1/17.
 */
public class UserQuery {

    private String name;

    public UserQuery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
