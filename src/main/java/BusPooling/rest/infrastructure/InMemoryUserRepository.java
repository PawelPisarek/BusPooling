package BusPooling.rest.infrastructure;

import BusPooling.rest.dao.User;
import BusPooling.rest.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian.perek@amu.edu.pl
 */
@Repository
public class InMemoryUserRepository implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    public InMemoryUserRepository() {
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User addUser(User user) {
        InMemoryUserRepository.users.add(user);
        return user;
    }
}
