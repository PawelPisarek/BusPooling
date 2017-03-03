package BusPooling.rest.repository;




import BusPooling.rest.infrastructure.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
public interface UserRepository {

    Collection<User> getUsers();
    User addUser(User user);

}
