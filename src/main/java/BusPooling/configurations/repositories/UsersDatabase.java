package BusPooling.configurations.repositories;



import BusPooling.configurations.data.User;

import java.util.List;

/**
 * Created by rafal on 4/4/16.
 */
public interface UsersDatabase {

    List<User> getUsers();
    void addUser(User user);
    User findByLogin(String username);
}
