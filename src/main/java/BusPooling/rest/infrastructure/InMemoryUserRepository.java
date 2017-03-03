package BusPooling.rest.infrastructure;

import BusPooling.rest.domain.User;
import BusPooling.rest.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
@Repository
public class InMemoryUserRepository {

    private static final List<User> users = new ArrayList<>();

    public InMemoryUserRepository() {
    }


    public List<User> getUsers() {
        return users;
    }


    public User addUser(User user) {
        InMemoryUserRepository.users.add(user);
        return user;
    }
}
