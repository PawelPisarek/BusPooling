package BusPooling.rest.infrastructure.DAO;

/**
 * Created by pawe on 3/15/17.
 */
public class RegisterPersonDAO {
    private String username;
    private String password;

    public RegisterPersonDAO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RegisterPersonDAO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
