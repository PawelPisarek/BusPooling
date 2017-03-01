package BusPooling.rest.repository;


import BusPooling.rest.dao.User;

import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
public interface UserRepository {

    List<User> getUsers();
    User addUser(User user);

}
