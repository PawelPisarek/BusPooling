package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.User.IUserQuery;
import BusPooling.rest.aplication.query.User.UserView.UserView;
import BusPooling.rest.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Created by pawe on 3/1/17.
 */
public class DbalUserQuery implements IUserQuery {

    private final UserRepository userRepository;

    public DbalUserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserView> getAll() {
        return this.userRepository.getUsers().stream()
                .map(user -> new UserView(user
                        .getName()))
                .collect(Collectors.toList());
    }
}
