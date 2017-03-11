package BusPooling.rest.infrastructure;

import BusPooling.rest.aplication.query.IUserQuery;
import BusPooling.rest.aplication.query.UserView.UserQuery;
import BusPooling.rest.repository.UserRepository;

import java.util.List;
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
    public List<UserQuery> getAll() {
        return this.userRepository.getUsers().stream()
                .map(user -> new UserQuery(user
                        .getFirstName()))
                .collect(Collectors.toList());
    }

}
