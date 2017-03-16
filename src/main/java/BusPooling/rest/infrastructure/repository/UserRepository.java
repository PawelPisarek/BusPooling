package BusPooling.rest.infrastructure.repository;

import BusPooling.configurations.data.User;
import BusPooling.configurations.repositories.UsersDatabase;
import BusPooling.rest.domain.Person;
import BusPooling.rest.infrastructure.entity.DelayedTransportEntity;
import BusPooling.rest.infrastructure.entity.PersonEntity;
import BusPooling.rest.repository.IRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by pawe on 3/15/17.
 */
public class UserRepository implements UsersDatabase {

    IRepository<Person, PersonEntity> personEntityIRepository;

    public UserRepository(IRepository<Person, PersonEntity> personEntityIRepository) {
        this.personEntityIRepository = personEntityIRepository;
    }

    public UserRepository() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
        personEntityIRepository = context.getBean("personEntityIRepository", IRepository.class);
    }

    @Override
    public List<User> getUsers() {
        return personEntityIRepository.getAll().stream().map(person -> new User(person.getId(), person.getUsername(), person.getPassword(), new Date().toString(), person.getGender(), new ArrayList(), new ArrayList(), person.isActive()))
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateUser(String id, User user) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User findByLogin(String username) {

        for (User i : this.getUsers()) {
            if (username.equals(i.getUsername())) {
                return i;
            }
        }
        throw new NotFoundException();
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
